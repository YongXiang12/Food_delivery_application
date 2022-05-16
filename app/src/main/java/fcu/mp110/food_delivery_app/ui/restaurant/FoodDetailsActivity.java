package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.review.ReviewActivity;

public class FoodDetailsActivity extends AppCompatActivity {

    private TextView tvAmount;
    private TextView tvDish;
    private TextView tvMark;
    private TextView tvPrice;
    private TextView tvComment;
    private ListView lvCustomization;
    private CustomizationAdapter adapter;

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
        adapter = new CustomizationAdapter(this,
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

    public void goReviewActivity(View view) {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }

    public void goCartActivity(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        List<DishesCustomizationItem> items = adapter.getDishesItems();
        int count = 0;
        for(int i=0; i<items.size(); i++) {
            if (items.get(i).isChecked()) {
                count = count + 1;
            }
        }
        intent.putExtra("checkbox", count);
        startActivity(intent);
    }
}