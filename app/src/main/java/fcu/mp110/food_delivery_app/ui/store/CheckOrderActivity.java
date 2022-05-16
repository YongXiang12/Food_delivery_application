package fcu.mp110.food_delivery_app.ui.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.search.SearchResultPage;

public class CheckOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
    }

    public void orderDetail(View view){
        Intent it = new Intent(this, OrderDetailActivity.class);
        startActivity(it);
    }

}