package fcu.mp110.food_delivery_app.ui.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantArrayAdapter;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantItem;
import fcu.mp110.food_delivery_app.ui.search.SearchResultPage;

public class CheckOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
        initFeaturedRestaurant("Mcdonald");

    }


    public void initFeaturedRestaurant(String name ) {
        //setOnClickListener();
        RecyclerView recyclerViewStore = this.findViewById(R.id.order_button_recycler);
        ArrayList<OrderClass> orderList = new ArrayList<>();
        OrderAdapt adapter = new OrderAdapt(this, orderList);
        recyclerViewStore.setAdapter(adapter);
        recyclerViewStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DatabaseReference restaurantRef;
        restaurantRef = FirebaseDatabase.getInstance()
                .getReference("Order").child(name);


        restaurantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        OrderClass order = dataSnapshot.getValue(OrderClass.class);
                        System.out.println(order.getUsername()) ;
                        orderList.add(order);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }



    public void orderDetail(View view){
        Intent it = new Intent(this, OrderDetailActivity.class);
        startActivity(it);
    }

}