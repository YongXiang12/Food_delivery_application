package fcu.mp110.food_delivery_app.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fcu.mp110.food_delivery_app.R;

public class CartItemsDataAdapter
        extends RecyclerView.Adapter<CartItemsDataAdapter.CartItemViewHolder> {
    public List<CartItem> cartItems;

    public class CartItemViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private TextView name;
        private TextView category;
        private TextView price;
        private ImageView image;

        public CartItemViewHolder(@NonNull View view) {
            super(view);
            context = view.getContext();
            name = (TextView) view.findViewById(R.id.tv_name);
            category = (TextView) view.findViewById(R.id.tv_category);
            price = (TextView) view.findViewById(R.id.tv_price);
            image = (ImageView) view.findViewById(R.id.imv_food_picture);
        }

    }

    public CartItemsDataAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new CartItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.name.setText(item.getName());
        holder.category.setText(item.getCategory());
        holder.price.setText("$"+item.getPrice());
        Glide.with(holder.context).load(item.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
