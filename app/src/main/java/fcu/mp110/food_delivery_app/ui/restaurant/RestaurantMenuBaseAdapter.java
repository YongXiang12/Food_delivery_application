package fcu.mp110.food_delivery_app.ui.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import fcu.mp110.food_delivery_app.R;

public class RestaurantMenuBaseAdapter extends BaseAdapter {
    Context c;
    int items[];

    public RestaurantMenuBaseAdapter(Context c, int[] arr) {
        this.c = c;
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
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.restaurant_menu_grid_layout, null);
        }
        ImageView imageView = view.findViewById(R.id.imv_thumbnail);
        imageView.setImageResource(items[i]);
        return view;
    }
}
