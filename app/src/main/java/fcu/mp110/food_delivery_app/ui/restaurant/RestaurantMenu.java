package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import fcu.mp110.food_delivery_app.R;

public class RestaurantMenu extends AppCompatActivity {

    int[] itemsarray = new int[]{
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
            R.drawable.seafood_pizza,R.drawable.ic_mcdonald
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        ImageView thumbnail = findViewById(R.id.imv_thumbnail);

        GridView gridView = findViewById(R.id.menu);

        // create a object of myBaseAdapter
        RestaurantMenuBaseAdapter baseAdapter = new RestaurantMenuBaseAdapter(this, itemsarray);
        gridView.setAdapter(baseAdapter);
    }
}