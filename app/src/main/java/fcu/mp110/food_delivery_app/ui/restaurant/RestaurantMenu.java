package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView txvStoreName = findViewById(R.id.txv_store_name);
        txvStoreName.setText(storeName);

        GridView gridView = findViewById(R.id.menu);

        // create a object of myBaseAdapter
        RestaurantMenuBaseAdapter baseAdapter = new RestaurantMenuBaseAdapter(this, itemsarray);
        gridView.setAdapter(baseAdapter);
    }

    public void goBack(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}