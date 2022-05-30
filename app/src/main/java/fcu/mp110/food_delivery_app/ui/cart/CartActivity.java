package fcu.mp110.food_delivery_app.ui.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.SwipeControllerActions;
import fcu.mp110.food_delivery_app.ui.order.OrderStatusActivity;

public class CartActivity extends AppCompatActivity implements IDrinkLoadListener {

    IDrinkLoadListener cartItemLoadListener;
    private CartItemsDataAdapter mAdapter;
    private List<CartItem> cartItems;
    private DatabaseReference mDatabase;
    private TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
//        int count = intent.getIntExtra("checkbox", 0);
//        tvDetail = findViewById(R.id.txv_detail);
//        tvDetail.setText(Integer.toString(count));

//        setPlayersDataAdapter();
        loadDrinkFromFirebase();
//        setupRecyclerView();
    }

    public void updateCartItems(int position, CartItem cartItem){
        cartItems.set(position, cartItem);
    }

    public void setOrderInfo() {
        int totalPrice = 0;
        int deliveryCharge = 10;
        int subtotal = cartItems.size();
        int discount = 0;
        for (CartItem c : cartItems) {
            totalPrice += c.getPrice();
            deliveryCharge += c.getAmount() * 3;
        }
        TextView tvSubtotal = this.findViewById(R.id.txv_subtotal_price);
        tvSubtotal.setText(String.valueOf(subtotal));
        TextView tvCharge = this.findViewById(R.id.txv_delivery_charge_price);
        tvCharge.setText("$" + String.valueOf(deliveryCharge));
        TextView tvDiscount = this.findViewById(R.id.txv_discount_price);
        tvDiscount.setText("$" + String.valueOf(discount));
        TextView tvTotal = this.findViewById(R.id.txv_total_price);
        tvTotal.setText("$" + String.valueOf(totalPrice));
    }

    private void setPlayersDataAdapter() {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            InputStreamReader is = new InputStreamReader(getAssets().open("players.csv"));

            BufferedReader reader = new BufferedReader(is);
            reader.readLine();
            String line;
            String[] st;
            while ((line = reader.readLine()) != null) {
                st = line.split(",");
                CartItem item = new CartItem();
                item.setName(st[0]);
//                item.setPrice(st[1]);
                item.setCategory(st[4]);
                item.setImage("https://www.highlandscoffee.com.vn/vnt_upload/product/04_2020/TRATHACHVAI_1.png");
                cartItems.add(item);
            }
        } catch (IOException e) {

        }

        mAdapter = new CartItemsDataAdapter(this, cartItems);
    }

    private void loadDrinkFromFirebase() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_oders);
        cartItems = new ArrayList<>();
        mAdapter = new CartItemsDataAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        FirebaseDatabase.getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                                CartItem item = orderSnapshot.getValue(CartItem.class);
                                item.setKey(orderSnapshot.getKey());
                                cartItems.add(item);
                            }
                            setOrderInfo();
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartItemLoadListener.onDrinkLoadFailed(error.getMessage());
                    }
                });
        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                String del = mAdapter.cartItems.get(position).getName();
                mAdapter.cartItems.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("UNIQUE_USER_ID");
//                Query applesQuery = ref.orderByChild("name").equalTo("CaramelMacchiato");
                Query applesQuery = ref.equalTo("CaramelMacchiato", "name");

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("TAG", "onCancelled", databaseError.toException());
                    }
                });
            }

            @Override
            public void onLeftClicked(int position) {
                mAdapter.cartItems.get(position).setName("TEST");
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_oders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mAdapter.cartItems.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }

            @Override
            public void onLeftClicked(int position) {
                mAdapter.cartItems.get(position).setName("TEST");
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    public void goBack(View view) {
        finish();
    }

    public void sendOrder(View view) {
        Intent intent = new Intent(this, OrderStatusActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDrinkLoadSuccess(List<CartItem> cartItemList) {
        TextView t = this.findViewById(R.id.textView7);
        t.setText(cartItemList.get(0).getName());
        mAdapter = new CartItemsDataAdapter(this,cartItemList);
//        setupRecyclerView();
    }

    @Override
    public void onDrinkLoadFailed(String message) {

    }
}