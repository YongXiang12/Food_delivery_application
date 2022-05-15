package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.order.OrderStatusActivity;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }

    public void goBack(View view) {
        finish();
    }

    public void sendOrder(View view){
        Intent intent = new Intent(this, OrderStatusActivity.class);
        startActivity(intent);
    }
}