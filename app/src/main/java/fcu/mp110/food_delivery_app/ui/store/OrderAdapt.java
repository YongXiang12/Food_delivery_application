package fcu.mp110.food_delivery_app.ui.store;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantArrayAdapter;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantMenu;

public class OrderAdapt extends RecyclerView.Adapter<OrderAdapt.MyViewHolder> {



        List<OrderClass> lists ;
        Context context ;


        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            Button button  ;

            // TextView modify_title ;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                context = itemView.getContext();
                button = itemView.findViewById(R.id.order_button);
                button.setOnClickListener(
                        this
                );

            }

            @Override
            public void onClick(View view) {

                System.out.println("hello");
                int pos = getAdapterPosition();
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("name",  lists.get(pos).getUsername());
                context.startActivity(intent);
            }


        }


    public OrderAdapt(Context ct, List<OrderClass> lists){
            this.context = ct ;
            this.lists = lists ;
        }



        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.order_button , parent , false);

            MyViewHolder viewHolder = new MyViewHolder(view);

            return viewHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.button.setText("訂單 "+Integer.toString((position+1)));
    }



    /*
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            System.out.println("position"+position);
            holder.name.setText(this.lists.get(position).getName());
            holder.price.setText("$ "+this.lists.get(position).getPrice());
            holder.img.setImageResource(this.lists.get(position).getImg());
            String c = Integer.toString(this.lists.get(position).getCount());
            holder.Count.setText(c);
            holder.item = lists.get(position) ;
            // holder.modify_title.setText("hello world");
        }
*/
        @Override
        public int getItemCount() {
            System.out.println(lists.size());
            return lists.size();
        }


    }