package fcu.mp110.food_delivery_app.ui.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fcu.mp110.food_delivery_app.R;

public class OrderDetailAdapt  extends RecyclerView.Adapter<OrderDetailAdapt.MyViewHolder> {

    List<UserDetailClass> lists ;
    Context context ;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name ;
        TextView price ;
        TextView Count ;
        // TextView modify_title ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.commodity_name);
            price = itemView.findViewById(R.id.order_price);
            Count = itemView.findViewById(R.id.order_account);
            // modify_title = itemView.findViewById(R.id.modify_title);
        }


    }


    public OrderDetailAdapt(Context ct, List<UserDetailClass> lists){
        this.context = ct ;
        this.lists = lists ;
    }



    @NonNull
    @Override
    public OrderDetailAdapt.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ordertextview , parent , false);

        return new OrderDetailAdapt.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println(lists.get(position).getName()+" "+lists.get(position).getPrice());
        holder.name.setText(lists.get(position).getName());
        holder.price.setText("$ "+Integer.toString(lists.get(position).getPrice()));
        holder.Count.setText(Integer.toString(lists.get(position).getAmounts()));

    }
    /*
    @Override
    public void onBindViewHolder(@NonNull ModifyAdapt.MyViewHolder holder, int position) {
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