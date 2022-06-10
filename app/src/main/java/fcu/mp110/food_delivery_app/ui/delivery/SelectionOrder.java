package fcu.mp110.food_delivery_app.ui.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
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
import java.util.List;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.order.UserOrder;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantItem;

public class SelectionOrder extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<UserOrder> orderList;
    public long count;
    public List<String> strlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_order);


        recyclerView = findViewById(R.id.order_rcy);
        orderList = new ArrayList<UserOrder>();
        strlist = new ArrayList<String>();
        SelectionOrderArrayAdapter adapter = new SelectionOrderArrayAdapter(strlist, orderList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        DatabaseReference orderRef;
        orderRef = FirebaseDatabase.getInstance()
                .getReference("Order").child("Mcdonald");

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        UserOrder orderItem = dataSnapshot.getValue(UserOrder.class);
                        //restaurantItem.setKey(dataSnapshot.getKey());
                        //restaurantItem.getRestaurantLabel();
                        orderList.add(orderItem);
                        if(!orderItem.getDelivererAccept()){
                            count++;
                        }
                    }
                    //adapter.notifyDataSetChanged();
                    for(long i=0; i<count; i++){
                        String str = "訂單#" + String.valueOf(i+1);
                        Log.v("MyApp", (str));
                        strlist.add(str);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public void reload(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        } else {
            Intent intent = getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);

            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }
    public void goBack(View view){
        finish();
    }
}