package fcu.mp110.food_delivery_app.ui.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.MainActivity;
import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantArrayAdapter;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantItem;

public class SearchResultPage extends AppCompatActivity {

    ArrayList<RestaurantItem> restaurantList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_page);
        Intent it  = getIntent();
        String name = it.getStringExtra("search_str");
        initFeaturedRestaurant(name);
    }

    public void initFeaturedRestaurant(String name ) {
        //setOnClickListener();
        RecyclerView recyclerViewStore = this.findViewById(R.id.search_page_recycle);
        restaurantList = new ArrayList<>();
        RestaurantArrayAdapter adapter = new RestaurantArrayAdapter(this, restaurantList);
        recyclerViewStore.setAdapter(adapter);
        recyclerViewStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DatabaseReference restaurantRef;
        restaurantRef = FirebaseDatabase.getInstance()
                .getReference("Restaurant");


        restaurantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                        RestaurantItem restaurantItem = dataSnapshot.getValue(RestaurantItem.class);
                        if(restaurantItem.getRestaurantName().contains(name)) {
                            restaurantItem.setKey(dataSnapshot.getKey());
                            restaurantItem.getRestaurantLabel();
                            restaurantList.add(restaurantItem);
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