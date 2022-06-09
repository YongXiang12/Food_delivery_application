package fcu.mp110.food_delivery_app.ui.store;

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

import fcu.mp110.food_delivery_app.R;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent it  = getIntent();
        String str = it.getStringExtra("name");
        initFeaturedRestaurant(str);
    }


    public void initFeaturedRestaurant(String name ) {
        //setOnClickListener();
        RecyclerView recyclerViewStore = this.findViewById(R.id.order_recycle);
        ArrayList<UserDetailClass> orderList = new ArrayList<>();
        OrderDetailAdapt adapter = new OrderDetailAdapt(this, orderList);
        recyclerViewStore.setAdapter(adapter);
        recyclerViewStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DatabaseReference restaurantRef;
        restaurantRef = FirebaseDatabase.getInstance()
                .getReference("Order").child("Mcdonald").child(name).child("orderDetail");


        restaurantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        UserDetailClass order = dataSnapshot.getValue(UserDetailClass.class);
                        orderList.add(order);
                        System.out.println("hello");
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }




}