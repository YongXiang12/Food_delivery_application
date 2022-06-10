package fcu.mp110.food_delivery_app.ui.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.order.UserOrder;

public class SelOrderDetailActivity extends AppCompatActivity {
    public int key;
    public RecyclerView recyclerView;
    public List<UserOrder> orderList;
    public UserOrder userOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_order_detail);
        Intent intent = getIntent();

        TextView store = findViewById(R.id.txv_storename);
        TextView cname = findViewById(R.id.txv_cname);
        TextView tprice = findViewById(R.id.txv_tprice);


        key = intent.getIntExtra("orderKey", 0);
        userOrder = (UserOrder) intent.getSerializableExtra("orderdetail");
        store.setText(userOrder.getRestaurant());
        cname.setText(userOrder.getUsername());
        tprice.setText(String.valueOf( userOrder.getTotalPrice()));

        recyclerView = findViewById(R.id.recycler1);
        SelOrderDetailArrayAdapter adapter = new SelOrderDetailArrayAdapter(userOrder.orderDetail, this);
        //SelectionOrderArrayAdapter adapter = new SelectionOrderArrayAdapter(strlist, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter.notifyDataSetChanged();

    }
    public void goBack(View view){
        finish();
    }
    public void accept(View view){
        userOrder.setDelivererAccept(true);
        DatabaseReference userCart = FirebaseDatabase
                .getInstance().getReference("Order").child("Mcdonald");
        userCart.child("UNIQUE_USER_ID")
                .setValue(userOrder);
        Snackbar.make(view, "接單成功", Snackbar.LENGTH_LONG).show();

    }
}