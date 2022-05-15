package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.MainActivity;
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

        Intent intent = getIntent();
        // get and set thumbnail picture
        int picresid = intent.getIntExtra("storepicture",0);
        ImageView thumbnail = findViewById(R.id.imv_thumbnail);
        thumbnail.setImageResource(picresid);
        // get and set store name
        String storeName = intent.getStringExtra("storename");
        TextView txvStoreName = findViewById(R.id.txv_dish);
        txvStoreName.setText(storeName);

        ArrayList<RestaurantMenuGridItem> menuGridItems = new ArrayList<RestaurantMenuGridItem>();
        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.seafood_pizza, "$100",
                "4.5 (30+)", "Pizza", "Chicken Cheese"));
        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.ic_mcdonald, "$85",
                "4.5 (30+)", "Fries", "fires"));
        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.seafood_pizza, "$90",
                "4.5 (30+)", "Pizza", "Chicken Cheese"));
        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.seafood_pizza, "$100",
                "4.5 (30+)", "hamburger", "Chicken Cheese"));

        // create a object of myBaseAdapter
        RestaurantMenuBaseAdapter baseAdapter = new RestaurantMenuBaseAdapter(this, menuGridItems);
        GridView gridView = findViewById(R.id.menu);
        gridView.setAdapter(baseAdapter);
    }

    public void goBack(View view)
    {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        finish();
    }

    public void goReviewActivity(View view) {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }

    public void goCartActivity(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}