package fcu.mp110.food_delivery_app.ui.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.R;

public class CategoriesResultActivity extends AppCompatActivity {
    private ArrayList<RestaurantItem> restaurantList;
    private String categoriesLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_result);

        Intent intent = getIntent();
        categoriesLabel = intent.getStringExtra("categoriesLabel");
        TextView textView = findViewById(R.id.categoriesResult);
        textView.setText(categoriesLabel);
        initCategoriesRestaurant();
    }
    public void goBack(View view)
    {
        finish();
    }

    public void initCategoriesRestaurant() {
        RecyclerView recyclerViewStore = this.findViewById(R.id.categoriesRecyclerView);
        restaurantList = new ArrayList<RestaurantItem>();
        CategoriesResultArrayAdapter adapter = new CategoriesResultArrayAdapter(CategoriesResultActivity.this, restaurantList);
        recyclerViewStore.setAdapter(adapter);
        recyclerViewStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DatabaseReference restaurantRef;
        restaurantRef = FirebaseDatabase.getInstance()
                .getReference("Restaurant");
        restaurantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String label;
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        RestaurantItem categoriesResultItem = dataSnapshot.getValue(RestaurantItem.class);
                        categoriesResultItem.setKey(dataSnapshot.getKey());
                        label = categoriesResultItem.getRestaurantLabel();
                        Log.v("MyApp", (label));
                        Log.v("MyApp", (categoriesLabel));
                        if(label.equals(categoriesLabel)){
                            Log.v("MyApp", (label));
                            Log.v("MyApp", (categoriesLabel));
                            restaurantList.add(categoriesResultItem);
                        }

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
//        RecyclerView recyclerViewStore = this.findViewById(R.id.store_recyclerview);
//        ArrayList<StoreItem> storeList = new ArrayList<StoreItem>();
//        storeList.add(new StoreItem(R.drawable.ic_mcdonald, "5.0", "A", "麥當勞", "hamburger"));
//        storeList.add(new StoreItem(R.drawable.hawwaipizza, "5.0", "B", "達美樂", "pizza"));
//        storeList.add(new StoreItem(R.drawable.seafood_pizza, "5.0", "B", "BB", "hamburger"));
//        storeList.add(new StoreItem(R.drawable.seafood_pizza, "5.0", "B", "BB", "hamburger"));
//        storeList.add(new StoreItem(R.drawable.seafood_pizza, "5.0", "B", "BB", "hamburger"));

    }
}