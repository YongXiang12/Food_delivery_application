package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.cart.CartActivity;
import fcu.mp110.food_delivery_app.ui.cart.CartItem;
import fcu.mp110.food_delivery_app.ui.cart.CartItemsDataAdapter;
import fcu.mp110.food_delivery_app.ui.review.ReviewActivity;

public class FoodDetailsActivity extends AppCompatActivity {

    private ImageView tvFoodPic;
    private String imgURL;
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
        tvFoodPic = findViewById(R.id.imv_detail_foodpic);
        imgURL = intent.getStringExtra("dishImgURI");
        Glide.with(this)
                .load(imgURL)
                .into(tvFoodPic);
        tvMark = findViewById(R.id.txv_mark);
        String mark = intent.getStringExtra("dishMark");
        tvMark.setText(mark);
        tvPrice = findViewById(R.id.food_price);
        String price = intent.getStringExtra("dishPrice");
        tvPrice.setText(price);
        tvComment = findViewById(R.id.txv_comment);
        tvComment.setText("Test");

        String restaurantKey = intent.getStringExtra("RestaurantKey");
        ArrayList<DishesCustomizationItem> customizationList =
                new ArrayList<DishesCustomizationItem>();
        adapter = new CustomizationAdapter(this,
                R.layout.dishes_customization, customizationList);
        lvCustomization = findViewById(R.id.lv_conditions);
        lvCustomization.setAdapter(adapter);

        DatabaseReference customizationRef;
        customizationRef = FirebaseDatabase.getInstance()
                .getReference("Customization")
                .child(restaurantKey);
        customizationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        DishesCustomizationItem dishesCustomizationItem = dataSnapshot.getValue(DishesCustomizationItem.class);
                        dishesCustomizationItem.setKey(dataSnapshot.getKey());
                        Log.v("MyApp", (dishesCustomizationItem.getCustomizationName()));
                        customizationList.add(dishesCustomizationItem);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

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
        DatabaseReference userCart = FirebaseDatabase
                .getInstance().getReference("Cart").child("UNIQUE_USER_ID");
        userCart.child(tvDish.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            Map<String,Object> updateDate = new HashMap<>();
                            String quantity = tvAmount.getText().toString();
                            updateDate.put("amount", Integer.parseInt(quantity));
                            Pattern p = Pattern.compile("\\d+");
                            String priceStr = tvPrice.getText().toString();
                            Matcher m = p.matcher(priceStr);
                            int price = 0;
                            while(m.find()) {
                                price = Integer.parseInt(m.group());
                            }
                            updateDate.put("price", price*Integer.parseInt(quantity));
                            updateDate.put("category", tvComment.getText().toString());
                            updateDate.put("image", imgURL);
                            updateDate.put("name", tvDish.getText().toString());
                            userCart.child(tvDish.getText().toString())
                                    .updateChildren(updateDate);
//                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void unused) {
//                                            Snackbar.make(findViewById(R.id.root),
//                                                    "Add to Cart Success!",
//                                                    Snackbar.LENGTH_LONG).show();
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Snackbar.make(findViewById(R.id.root),
//                                                    "Add to Cart Fail!",
//                                                    Snackbar.LENGTH_LONG).show();
//                                        }
//                                    });
                        }
                        else
                        {
                            Map<String,Object> updateDate = new HashMap<>();
                            String quantity = tvAmount.getText().toString();
                            updateDate.put("amount", Integer.parseInt(quantity));
                            Pattern p = Pattern.compile("\\d+");
                            String priceStr = tvPrice.getText().toString();
                            Matcher m = p.matcher(priceStr);
                            int price = 0;
                            while(m.find()) {
                                price = Integer.parseInt(m.group());
                            }
                            updateDate.put("price", price*Integer.parseInt(quantity));
                            updateDate.put("category", tvComment.getText().toString());
                            updateDate.put("image", imgURL);
                            updateDate.put("name", tvDish.getText().toString());
                            userCart.child(tvDish.getText().toString())
                                    .setValue(updateDate);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        startActivity(intent);
    }
}