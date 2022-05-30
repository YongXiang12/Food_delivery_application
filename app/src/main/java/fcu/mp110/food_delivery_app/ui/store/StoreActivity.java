package fcu.mp110.food_delivery_app.ui.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.search.SearchResultPage;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
    }

    public void CheckOrder(View view){
        Intent intent = new Intent(this , CheckOrderActivity.class);
        startActivity(intent);
    }

    public void ModitfyItems(View view){
        Intent intent = new Intent(this , ModifyItemActivity.class);
        startActivity(intent);
    }



}