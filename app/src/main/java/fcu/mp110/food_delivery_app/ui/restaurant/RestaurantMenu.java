package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.cart.CartActivity;
import fcu.mp110.food_delivery_app.ui.review.ReviewActivity;

public class RestaurantMenu extends AppCompatActivity {

    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        Intent intent = getIntent();
        // get and set thumbnail picture
        String imgURI = intent.getStringExtra("restaurantImgURI");
        ImageView thumbnail = findViewById(R.id.imv_thumbnail);
        Glide.with(this)
                .load(imgURI)
                .into(thumbnail);
        // get and set store name
        restaurantName = intent.getStringExtra("restaurantName");
        TextView txvRestaurantName = findViewById(R.id.txv_dish);
        txvRestaurantName.setText(restaurantName);

        String deliveryTime = intent.getStringExtra("deliveryTime");
        TextView txvdeliveryTime = findViewById(R.id.txv_delivery_time);
        txvdeliveryTime.setText("外送" + deliveryTime + "分鐘");

        String restaurantScore = intent.getStringExtra("restaurantScore");
        String restaurantCommentNum = intent.getStringExtra("restaurantCommentNum");
        //Log.v("MyApp", (restaurantScore+restaurantCommentNum));
        TextView txv_mark = (TextView) findViewById(R.id.txv_mark);
        txv_mark.setText(restaurantScore+restaurantCommentNum);

        ArrayList<RestaurantMenuGridItem> menuGridItems = new ArrayList<RestaurantMenuGridItem>();

        // create a object of myBaseAdapter
        String restaurantKey = intent.getStringExtra("restaurantKey");
        RestaurantMenuBaseAdapter baseAdapter = new RestaurantMenuBaseAdapter(this,
                restaurantName, menuGridItems);
        GridView gridView = findViewById(R.id.menu);
        gridView.setAdapter(baseAdapter);
        DatabaseReference menuRef;
        menuRef = FirebaseDatabase.getInstance()
                .getReference("Menu")
                .child(restaurantKey);
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        RestaurantMenuGridItem menuItem = dataSnapshot.getValue(RestaurantMenuGridItem.class);
                        menuItem.setKey(dataSnapshot.getKey());
                        menuItem.setRestaurantKey(restaurantKey);
                        //Log.v("MyApp", (menuItem.getMenuImgURI()));
                        menuGridItems.add(menuItem);

                    }
                    baseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void goBack(View view)
    {
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
//    int[] itemsarray = new int[]{
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald,
//            R.drawable.seafood_pizza,R.drawable.ic_mcdonald
//    };
//        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.seafood_pizza, "$100",
//                "4.5 (30+)", "Pizza", "Chicken Cheese"));
//        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.ic_mcdonald, "$85",
//                "4.5 (30+)", "Fries", "fires"));
//        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.seafood_pizza, "$90",
//                "4.5 (30+)", "Pizza", "Chicken Cheese"));
//        menuGridItems.add(new RestaurantMenuGridItem(R.drawable.seafood_pizza, "$100",
//                "4.5 (30+)", "hamburger", "Chicken Cheese"));