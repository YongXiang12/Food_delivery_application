package fcu.mp110.food_delivery_app.ui.restaurant;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fcu.mp110.food_delivery_app.R;

public class CustomizationAdapter extends ArrayAdapter<DishesCustomizationItem> {

    private Context context;
    private List<DishesCustomizationItem> dishesItems;

    public CustomizationAdapter(@NonNull Context context, int resource, List<DishesCustomizationItem> dishesItem) {
        super(context, resource, dishesItem);
        this.context = context;
        this.dishesItems = dishesItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout itemLayout = null;

        if (convertView == null) {
            itemLayout = (LinearLayout) inflater.inflate(R.layout.dishes_customization, null);
        } else {
            itemLayout = (LinearLayout) convertView;
        }

        DishesCustomizationItem item = dishesItems.get(position);
        ImageView ivDescribe = itemLayout.findViewById(R.id.imv_describe);
        ivDescribe.setImageResource(item.getImgResId());
        TextView tvCustomization = itemLayout.findViewById(R.id.txv_customization);
        tvCustomization.setText(item.getCustomization());
        TextView tvAddPrice = itemLayout.findViewById(R.id.txv_add_price);
        tvAddPrice.setText(item.getPrice());
        CheckBox rbtChoice = itemLayout.findViewById(R.id.chbt_choice);
        rbtChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the view now checked?
                boolean checked = ((CheckBox) view).isChecked();
                // Check which checkbox was clicked
                if (checked) {
                    item.setChecked(true);
                    System.out.print("true");
                } else {
                    item.setChecked(false);
                }
                dishesItems.set(position, item);
            }
        });
        return itemLayout;
    }

    public List<DishesCustomizationItem> getDishesItems() {
        return dishesItems;
    }
}
