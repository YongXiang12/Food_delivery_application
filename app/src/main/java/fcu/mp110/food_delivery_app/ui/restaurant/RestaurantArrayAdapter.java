package fcu.mp110.food_delivery_app.ui.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fcu.mp110.food_delivery_app.R;

public class RestaurantArrayAdapter extends RecyclerView.Adapter<RestaurantArrayAdapter.ViewHolder> {
    public List<StoreItem> storeItemList;
    public Context context;


    public RestaurantArrayAdapter(Context context, List<StoreItem> storeItems) {
        this.context = context;
        this.storeItemList = storeItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView restaurantImg;
        public TextView restaurantName;
        public TextView restaurantStar;
        public TextView restaurantLabel;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantImg = itemView.findViewById(R.id.store_item_storeImg);
            restaurantName = itemView.findViewById(R.id.store_item_storeName);
            restaurantStar = itemView.findViewById(R.id.store_ltem_star);
            restaurantLabel = itemView.findViewById(R.id.store_item_label);
            cardView = itemView.findViewById(R.id.cardview_restaurant);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Intent intent = new Intent(context, RestaurantMenu.class);
            intent.putExtra("storename", storeItemList.get(pos).getStoreName());
            intent.putExtra("storepicture", storeItemList.get(pos).getStoreImgResId());
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
// Get the Subject based on the current position
        StoreItem item = storeItemList.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.restaurantImg;
        imageView.setImageResource(item.getStoreImgResId());

        TextView txv_Name = holder.restaurantName;
        txv_Name.setText(item.getStoreName());

        TextView txv_Star = holder.restaurantStar;
        txv_Star.setText(item.getStar());

        TextView txv_Label = holder.restaurantLabel;
        txv_Label.setText(item.getStoreLabel());
    }

    @Override
    public int getItemCount() {
        return storeItemList.size();
    }


}
