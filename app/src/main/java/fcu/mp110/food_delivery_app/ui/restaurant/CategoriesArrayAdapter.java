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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fcu.mp110.food_delivery_app.R;

public class CategoriesArrayAdapter extends RecyclerView.Adapter<CategoriesArrayAdapter.ViewHolder>{
    public List<CategoriesItem> categoriesItemList;
    public Context context;


    public CategoriesArrayAdapter(Context context, List<CategoriesItem> categoriesItems) {
        this.context = context;
        this.categoriesItemList = categoriesItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView categoriesImg;
        public TextView categoriesName;

        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriesImg = itemView.findViewById(R.id.img_categories);
            categoriesName = itemView.findViewById(R.id.txv_categoriesname);
            cardView = itemView.findViewById(R.id.cardview_categories);

            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
//            Intent intent = new Intent(context, RestaurantMenu.class);
//            intent.putExtra("storename", storeItemList.get(pos).getStoreName());
//            intent.putExtra("storepicture", storeItemList.get(pos).getStoreImgResId());
//            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public CategoriesArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorieslistitem, parent, false);

        // Return a new holder instance
        CategoriesArrayAdapter.ViewHolder viewHolder = new CategoriesArrayAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesArrayAdapter.ViewHolder holder, int position) {
// Get the Subject based on the current position
        CategoriesItem item = categoriesItemList.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.categoriesImg;
        imageView.setImageResource(item.getCategoriesImgResId());

        TextView txv_Name = holder.categoriesName;
        txv_Name.setText(item.getCategories());


        switch (position){
            case 0:
                break;
            case 1:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(), R.color.green));
                break;
            case 2:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(), R.color.blue));
                break;
            case 3:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(), R.color.pink));
                break;
            case 4:
                holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.cardView.getContext(), R.color.per));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return categoriesItemList.size();
    }
}
