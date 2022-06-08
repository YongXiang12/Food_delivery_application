package fcu.mp110.food_delivery_app.ui.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.cart.CartItem;

public class RestaurantMenuBaseAdapter extends BaseAdapter {
    Context context;
    private List<RestaurantMenuGridItem> gridItems;
    private String restaurantName;

    public RestaurantMenuBaseAdapter(Context c,String name, List<RestaurantMenuGridItem> gridItems) {
        this.context = c;
        this.restaurantName = name;
        this.gridItems = gridItems;
    }

    @Override
    public int getCount() {
        return gridItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.restaurant_menu_grid_layout, null);
        }

        RestaurantMenuGridItem item = gridItems.get(i);
        // set info
        ImageView imageView = view.findViewById(R.id.imv_thumbnail);
        Glide.with(context)
                .load(item.getMenuImgURI())
                .into(imageView);

        TextView tvPrice = view.findViewById(R.id.txv_dish_price);
        tvPrice.setText(item.getPrice());
//        TextView tvMark = view.findViewById(R.id.txv_dish_review);
//        tvMark.setText(item.getTvMark());
        TextView tvDishName = view.findViewById(R.id.txv_dish_name);
        tvDishName.setText(item.getDishName());
//        TextView tvIngredient = view.findViewById(R.id.txv_dish_ingredients);
//        tvIngredient.setText(item.getIngredient());
        // set setOnClickListener
        CardView cardView = view.findViewById(R.id.cardview_food);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailsActivity.class);
                intent.putExtra("dishName", item.getDishName());
                intent.putExtra("dishPrice", item.getPrice());
//                intent.putExtra("dishMark", item.getTvMark());
                intent.putExtra("RestaurantName", restaurantName);
                intent.putExtra("dishImgURI", item.getMenuImgURI());
                intent.putExtra("RestaurantKey", item.getRestaurantKey());
                context.startActivity(intent);
            }
        });
        ToggleButton tbnFavorite = view.findViewById(R.id.toggle_favorite);
        FirebaseDatabase
                .getInstance()
                .getReference("Favorite")
                .child("UNIQUE_USER_ID")
                .child(item.getDishName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Map<String, Boolean> data = (Map<String, Boolean>) snapshot.getValue();
                            Boolean likeState = data.get("like") ;
                            tbnFavorite.setChecked(likeState);
                        } else {
                            tbnFavorite.setChecked(false);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//        Boolean tbnFavoriteState = tbnFavorite.isChecked();
        tbnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tvDishName.setText(item.getTvDishName()+"f");
                setFavorite(item, tbnFavorite.isChecked());
            }
        });
        return view;
    }

    private void setFavorite(RestaurantMenuGridItem restaurantMenuGridItem, Boolean state) {
        Boolean toggleButtonState = state;
        RestaurantMenuGridItem item = restaurantMenuGridItem;
        DatabaseReference userFavorite = FirebaseDatabase
                .getInstance().getReference("Favorite").child("UNIQUE_USER_ID");
        userFavorite.child(item.getDishName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Map<String, Object> updateDate = new HashMap<>();
                            Boolean newState = toggleButtonState;
                            updateDate.put("like", newState);
                            userFavorite.child(item.getDishName())
                                    .updateChildren(updateDate);
                        } else {
                            Map<String, Object> updateDate = new HashMap<>();
                            Boolean newState = toggleButtonState;
                            updateDate.put("like", newState);
                            userFavorite.child(item.getDishName())
                                    .updateChildren(updateDate);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
