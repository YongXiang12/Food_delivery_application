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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fcu.mp110.food_delivery_app.R;

public class CategoriesResultArrayAdapter extends RecyclerView.Adapter<CategoriesResultArrayAdapter.ViewHolder> {
    public List<RestaurantItem> categoriesResultItemList;
    public Context context;


    public CategoriesResultArrayAdapter(CategoriesResultActivity context, ArrayList<RestaurantItem> categoriesResultItems) {
        this.context = context;
        this.categoriesResultItemList = categoriesResultItems;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView restaurantImg;
        public TextView restaurantName;
        public TextView restaurantStar;
        public TextView restaurantLabel;
        public TextView txv_commentNum;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantImg = itemView.findViewById(R.id.store_item_storeImg);
            restaurantName = itemView.findViewById(R.id.store_item_storeName);
            restaurantStar = itemView.findViewById(R.id.store_ltem_star);
            restaurantLabel = itemView.findViewById(R.id.store_item_label);
            txv_commentNum = itemView.findViewById(R.id.commentNum);
            cardView = itemView.findViewById(R.id.cardview_restaurant);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Intent intent = new Intent(context, RestaurantMenu.class);
            intent.putExtra("deliveryTime",  categoriesResultItemList.get(pos).getDeliveryTime());
            intent.putExtra("restaurantKey", categoriesResultItemList.get(pos).getKey());
            intent.putExtra("restaurantName", categoriesResultItemList.get(pos).getRestaurantName());
            intent.putExtra("restaurantImgURI", categoriesResultItemList.get(pos).getRestaurantImgURI());
            intent.putExtra("restaurantScore", categoriesResultItemList.get(pos).getRestaurantScore());
            intent.putExtra("restaurantCommentNum", categoriesResultItemList.get(pos).getRestaurantCommentNum());
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoriesresultitem, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
// Get the Subject based on the current position
        RestaurantItem item = categoriesResultItemList.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.restaurantImg;
        Glide.with(context)
                .load(item.getRestaurantImgURI())
                .into(imageView);

        TextView txv_Name = holder.restaurantName;
        txv_Name.setText(item.getRestaurantName());

        TextView txv_Star = holder.restaurantStar;
        txv_Star.setText(item.getRestaurantScore());

        TextView txv_Label = holder.restaurantLabel;
        txv_Label.setText(item.getRestaurantLabel());

        TextView txv_commentNum = holder.txv_commentNum;
        txv_commentNum.setText(item.getRestaurantCommentNum());
    }

    @Override
    public int getItemCount() {
        return categoriesResultItemList.size();
    }
}


