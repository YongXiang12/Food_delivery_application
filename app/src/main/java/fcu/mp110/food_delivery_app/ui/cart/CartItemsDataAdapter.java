package fcu.mp110.food_delivery_app.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Context mContext;

    public class CartItemViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private TextView name;
        private TextView category;
        private TextView price;
        private ImageView image;
        private TextView amount;
        private Button add;
        private Button remove;

        public CartItemViewHolder(@NonNull View view) {
            super(view);
            context = view.getContext();
            name = (TextView) view.findViewById(R.id.tv_name);
            category = (TextView) view.findViewById(R.id.tv_category);
            price = (TextView) view.findViewById(R.id.tv_price);
            image = (ImageView) view.findViewById(R.id.imv_food_picture);
            amount = (TextView) view.findViewById(R.id.tv_amount);
            add = (Button) view.findViewById(R.id.btn_add);
            remove = (Button) view.findViewById(R.id.btn_remove);
        }

    }

    public CartItemsDataAdapter(Context context, List<CartItem> cartItems) {
        this.cartItems = cartItems;
        this.mContext = context;
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
        int pos = position;
        CartItem item = cartItems.get(position);
        holder.name.setText(item.getName());
        holder.category.setText(item.getCategory());
        holder.price.setText("$"+String.valueOf(item.getPrice()));
        holder.amount.setText(String.valueOf(item.getAmount()));
        int unit = item.getPrice() / item.getAmount();
        Glide.with(holder.context).load(item.getImage())
                .into(holder.image);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(holder.amount.getText().toString());
                amount += 1;
                holder.amount.setText(String.valueOf(amount));
                int price = unit * amount;
                holder.price.setText("$"+String.valueOf(price));
                item.setAmount(amount);
                item.setPrice(price);
                if (mContext instanceof CartActivity) {
                    ((CartActivity)mContext).updateCartItems(pos,item);
                    ((CartActivity)mContext).setOrderInfo();
                }
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(holder.amount.getText().toString());
                if (amount > 1)
                    amount -= 1;
                holder.amount.setText(String.valueOf(amount));
                int price = unit * amount;
                holder.price.setText("$"+String.valueOf(price));
                item.setAmount(amount);
                item.setPrice(price);
                if (mContext instanceof CartActivity) {
                    ((CartActivity)mContext).updateCartItems(pos,item);
                    ((CartActivity)mContext).setOrderInfo();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
