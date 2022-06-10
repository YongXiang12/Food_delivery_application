package fcu.mp110.food_delivery_app.ui.delivery;

import android.content.Context;
import android.content.Intent;
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
import java.util.Map;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantArrayAdapter;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantItem;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantMenu;

public class SelOrderDetailArrayAdapter extends RecyclerView.Adapter<SelOrderDetailArrayAdapter.ViewHolder> {
    public List<Object> itemList;
    public Context context;

    public SelOrderDetailArrayAdapter(List<Object> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txv_name);
            price = itemView.findViewById(R.id.txv_price);
            num = itemView.findViewById(R.id.txv_num);

        }

    }

    @NonNull
    @Override
    public SelOrderDetailArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selorderdetailitem, parent, false);

        // Return a new holder instance
            SelOrderDetailArrayAdapter.ViewHolder viewHolder = new SelOrderDetailArrayAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelOrderDetailArrayAdapter.ViewHolder holder, int position) {
// Get the Subject based on the current position
        Map<String,Object> item = (Map<String, Object>) itemList.get(position);

        // Setting views with the corresponding data
        TextView txv_Name = holder.name;
        txv_Name.setText((String)item.get("name"));

        TextView txv_price = holder.price;
        txv_price.setText((String)item.get("price").toString());


        TextView txv_num = holder.num;
        txv_num.setText((String)item.get("amounts").toString());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

