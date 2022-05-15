package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.R;

public class FoodDetailsActivity extends AppCompatActivity {

    private TextView tvAmount;
    private TextView tvDish;
    private TextView tvMark;
    private TextView tvPrice;
    private TextView tvComment;
    private ListView lvCustomization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        tvAmount = findViewById(R.id.txv_amount);
        tvDish = findViewById(R.id.txv_dish);
        Intent intent = getIntent();
        String name = intent.getStringExtra("dishName");
        tvDish.setText(name);
        tvMark = findViewById(R.id.txv_mark);
        String mark = intent.getStringExtra("dishMark");
        tvMark.setText(mark);
        tvPrice = findViewById(R.id.food_price);
        String price = intent.getStringExtra("dishPrice");
        tvPrice.setText(price);
        tvComment = findViewById(R.id.txv_comment);
        tvComment.setText("Test");

        ArrayList<DishesCustomizationItem> customizationList =
                new ArrayList<DishesCustomizationItem>();
        customizationList.add(new DishesCustomizationItem(R.drawable.icons8_star_96px,
                "Pepper Julienned", "+$10"));
        customizationList.add(new DishesCustomizationItem(R.drawable.icons8_star_96px,
                "Baby Spinach", "+$10"));
        customizationList.add(new DishesCustomizationItem(R.drawable.icons8_star_96px,
                "Mushroom", "+$15"));
        CustomizationAdapter adapter = new CustomizationAdapter(this,
                R.layout.dishes_customization, customizationList);
        lvCustomization = findViewById(R.id.lv_conditions);
        lvCustomization.setAdapter(adapter);

    }

    public void goBack(View view) {
        finish();
    }

    public void addAmount(View view) {
        String amountStr = tvAmount.getText().toString();
        int amountInt = Integer.parseInt(amountStr);
        amountInt = amountInt + 1;
        amountStr = Integer.toString(amountInt);
        tvAmount.setText(amountStr);
    }

    public void removeAmount(View view) {
        String amountStr = tvAmount.getText().toString();
        int amountInt = Integer.parseInt(amountStr);
        if (amountInt > 1) {
            amountInt = amountInt - 1;
            amountStr = Integer.toString(amountInt);
        }
        tvAmount.setText(amountStr);
    }
}