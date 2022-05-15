package fcu.mp110.food_delivery_app.ui.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import fcu.mp110.food_delivery_app.R;

public class RestaurantMenuBaseAdapter extends BaseAdapter {
    Context context;
    int items[];

    public RestaurantMenuBaseAdapter(Context c, int[] arr) {
        this.context = c;
        this.items = arr;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.restaurant_menu_grid_layout, null);
        }
        ImageView imageView = view.findViewById(R.id.imv_thumbnail);
        CardView cardView = view.findViewById(R.id.cardview_food);
        imageView.setImageResource(items[i]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailsActivity.class);
                TextView tvName = view.findViewById(R.id.txv_dish_name);
                intent.putExtra("dishName", tvName.getText().toString());
                TextView tvPrice = view.findViewById(R.id.txv_dish_price);
                intent.putExtra("dishPrice", tvPrice.getText().toString());
                TextView tvMark = view.findViewById(R.id.txv_dish_review);
                intent.putExtra("dishMark", tvMark.getText().toString());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
