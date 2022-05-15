package fcu.mp110.food_delivery_app.ui.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.List;

import fcu.mp110.food_delivery_app.R;

public class RestaurantMenuBaseAdapter extends BaseAdapter {
    Context context;
    private List<RestaurantMenuGridItem> gridItems;

    public RestaurantMenuBaseAdapter(Context c, List<RestaurantMenuGridItem> gridItems) {
        this.context = c;
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
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.restaurant_menu_grid_layout, null);
        }

        RestaurantMenuGridItem item = gridItems.get(i);
        // set info
        ImageView imageView = view.findViewById(R.id.imv_thumbnail);
        imageView.setImageResource(item.getImgResId());
        TextView tvPrice = view.findViewById(R.id.txv_dish_price);
        tvPrice.setText(item.getTvPrice());
        TextView tvMark = view.findViewById(R.id.txv_dish_review);
        tvMark.setText(item.getTvMark());
        TextView tvDishName = view.findViewById(R.id.txv_dish_name);
        tvDishName.setText(item.getTvDishName());
        TextView tvIngredient = view.findViewById(R.id.txv_dish_ingredients);
        tvIngredient.setText(item.getTvIngredient());
        // set setOnClickListener
        CardView cardView = view.findViewById(R.id.cardview_food);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailsActivity.class);
                intent.putExtra("dishName", item.getTvDishName());
                intent.putExtra("dishPrice", item.getTvPrice());
                intent.putExtra("dishMark", item.getTvMark());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
