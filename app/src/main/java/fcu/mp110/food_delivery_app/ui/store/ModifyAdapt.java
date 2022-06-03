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

public class ModifyAdapt extends RecyclerView.Adapter<ModifyAdapt.MyViewHolder> {

    List<ModifyItems> lists ;
    Context context ;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name ;
        TextView price ;
        ImageView img ;
        TextView Count ;
        ModifyItems item ;
       // TextView modify_title ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.food_name);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.food_image);
            Count = itemView.findViewById(R.id.Count);
           // modify_title = itemView.findViewById(R.id.modify_title);

        }


    }


    public ModifyAdapt(Context ct, List<ModifyItems> lists){
        this.context = ct ;
        this.lists = lists ;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.modify_row , parent , false);

        return new MyViewHolder(view);
    }

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

    @Override
    public int getItemCount() {
        System.out.println(lists.size());
        return lists.size();
    }


}
