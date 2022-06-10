package fcu.mp110.food_delivery_app.ui.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fcu.mp110.food_delivery_app.R;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
    }
    public void SelectionOrder(View view){
        Intent intent = new Intent(DeliveryActivity.this, SelectionOrder.class);
        startActivity(intent);
    }

}