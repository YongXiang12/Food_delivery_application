package fcu.mp110.food_delivery_app.ui.delivery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.order.UserOrder;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantArrayAdapter;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantItem;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantMenu;

public class SelectionOrderArrayAdapter extends RecyclerView.Adapter<SelectionOrderArrayAdapter.ViewHolder>{
    public List<String> strings;
    public Context context;
    public List<UserOrder> userOrderList;

    public SelectionOrderArrayAdapter(List<String> strings, List<UserOrder> userOrderList, Context context) {
        this.strings = strings;
        this.context = context;
        this.userOrderList = userOrderList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView num;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.txv_orderno);
            cardView = itemView.findViewById(R.id.card1);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Intent intent = new Intent(context, SelOrderDetailActivity.class);
            intent.putExtra("orderKey",  pos);
            intent.putExtra("orderdetail" , userOrderList.get(pos));
            context.startActivity(intent);
//            intent.putExtra("restaurantKey", restaurantItemList.get(pos).getKey());
//            intent.putExtra("restaurantName", restaurantItemList.get(pos).getRestaurantName());
//            intent.putExtra("restaurantImgURI", restaurantItemList.get(pos).getRestaurantImgURI());
//            intent.putExtra("restaurantScore", restaurantItemList.get(pos).getRestaurantScore());
//            intent.putExtra("restaurantCommentNum", restaurantItemList.get(pos).getRestaurantCommentNum());
//            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public SelectionOrderArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delivery_orderlistitem, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
// Get the Subject based on the current position
        String str = strings.get(position);

        TextView txv_Name = holder.num;
        Log.v("MyApp", String.valueOf((str)));
        txv_Name.setText(str);

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


}
