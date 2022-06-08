package fcu.mp110.food_delivery_app.ui.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fcu.mp110.food_delivery_app.R;
import fcu.mp110.food_delivery_app.ui.order.OrderStatusActivity;
import fcu.mp110.food_delivery_app.ui.order.UserOrder;
import fcu.mp110.food_delivery_app.ui.restaurant.FoodDetailsActivity;

public class CartActivity extends AppCompatActivity implements IDrinkLoadListener {

    IDrinkLoadListener cartItemLoadListener;
    private CartItemsDataAdapter mAdapter;
//    private List<CartItem> cartItems;
    private DatabaseReference mDatabase;
    private TextView tvDetail;
    private String imgURL;
    private String restaurantKey;
    private String name;
    private String mark;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();


//        setPlayersDataAdapter();
        loadDrinkFromFirebase();
//        setupRecyclerView();
    }

    public void updateCartItems(int position, CartItem cartItem){
//        cartItems.set(position, cartItem);
    }

    public void setOrderInfo() {
        int totalPrice = 0;
        int deliveryCharge = 10;
        int subtotal = mAdapter.cartItems.size();
        int discount = 0;
        for (CartItem c : mAdapter.cartItems) {
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
//        List<CartItem> cartItems = new ArrayList<>();
//        try {
//            InputStreamReader is = new InputStreamReader(getAssets().open("players.csv"));
//
//            BufferedReader reader = new BufferedReader(is);
//            reader.readLine();
//            String line;
//            String[] st;
//            while ((line = reader.readLine()) != null) {
//                st = line.split(",");
//                CartItem item = new CartItem();
//                item.setName(st[0]);
////                item.setPrice(st[1]);
//                item.setCategory(st[4]);
//                item.setImage("https://www.highlandscoffee.com.vn/vnt_upload/product/04_2020/TRATHACHVAI_1.png");
//                cartItems.add(item);
//            }
//        } catch (IOException e) {
//
//        }

//        mAdapter = new CartItemsDataAdapter(this,cartItems);
        mAdapter.notifyDataSetChanged();
    }

    private void loadDrinkFromFirebase() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_oders);
        List<CartItem> cartItems = new ArrayList<>();
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
                            mAdapter.notifyDataSetChanged();
                            setOrderInfo();
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
//                String del = mAdapter.cartItems.get(position).getName();
                String del = cartItems.get(position).getName();
                cartItems.remove(position);
                AlertDialog dialog = new AlertDialog.Builder(findViewById(R.id.root).getContext())
                        .setTitle("Delete item")
                        .setMessage("Do you really want to delete item")
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance()
                                        .getReference("Cart")
                                        .child("UNIQUE_USER_ID")
                                        .child(del)
                                        .removeValue();
//                                cartItems = new ArrayList<>();

//                                mAdapter.removeItem(position);
//                                cartItems.remove(position);
//                                mAdapter.notifyDataSetChanged();
//                                mAdapter.notifyItemRemoved(position);
//                                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
                                dialogInterface.dismiss();
                            }
                        }).create();
                dialog.show();
//                mAdapter = new CartItemsDataAdapter(CartActivity.this, cartItems);
//                recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
//                recyclerView.setAdapter(mAdapter);
//                mAdapter.removeItem(position);
//                cartItems.remove(position);
                mAdapter.notifyDataSetChanged();
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }

            @Override
            public void onLeftClicked(int position) {
//                mAdapter.cartItems.get(position).setName("TEST");
//                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
                finish();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(findViewById(R.id.root).getContext());
        builder.setTitle("One more step!");

        View viewForCheck = LayoutInflater.from(findViewById(R.id.root).getContext())
                .inflate(R.layout.layout_place_order, null);

        EditText edtAddress = (EditText) viewForCheck.findViewById(R.id.edt_address);
        RadioButton rdiHome = (RadioButton) viewForCheck.findViewById(R.id.rdi_home_address);
        RadioButton rdiOtherAddress = (RadioButton) viewForCheck.findViewById(R.id.rdi_other_address);
        RadioButton rdiShipToThis = (RadioButton) viewForCheck.findViewById(R.id.rdi_ship_this_address);
        Spinner spiChooseRestaurant = (Spinner) viewForCheck.findViewById(R.id.spi_choose_restaurant);

        DatabaseReference restaurantReference = FirebaseDatabase
                .getInstance().getReference("Restaurant");

//        List<String> tempSet = new ArrayList<String>();
        Set<String> words = new LinkedHashSet<>();
        for(CartItem cartItem:mAdapter.cartItems){
            String key = cartItem.getCategory();
//            tempSet.add(key);
            words.add(key);
        }
        
//        String[] stringArray = tempSet.toArray(new String[0]);
        String[] stringArray = words.toArray(new String[0]);
        ArrayAdapter<String> tempAd = new ArrayAdapter<>(CartActivity.this,
                android.R.layout.simple_spinner_item, stringArray);
        tempAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiChooseRestaurant.setAdapter(tempAd);
        spiChooseRestaurant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtAddress.setText("407台中市西屯區文華路100號");


        rdiHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    edtAddress.setText("407台中市西屯區文華路100號");
                }
            }
        });
        rdiOtherAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    edtAddress.setText(""); //Clear
                    edtAddress.setHint("Enter your address");
                }
            }
        });
        rdiShipToThis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    Toast.makeText(findViewById(R.id.root).getContext(),
                            "Implement late with Google API", Toast.LENGTH_SHORT).show();
                }
            }
        });



        builder.setView(viewForCheck);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(findViewById(R.id.root).getContext(),
                        "Implrment late", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


//        DatabaseReference userCart = FirebaseDatabase
//                .getInstance().getReference("Order").child("Mcdonald");
//        userCart.child("UNIQUE_USER_ID")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists())
//                        {
////                            Map<String,Object> updateDate = new HashMap<>();
//
//                            List<Object> detailUpdateDate = new ArrayList<>();
//                            for(CartItem cartItem:mAdapter.cartItems){
//                                Map<String,Object> detail = new HashMap<>();
//                                detail.put("name",cartItem.getName());
//                                detail.put("price",cartItem.getPrice());
//                                detail.put("amounts",cartItem.getAmount());
//                                detailUpdateDate.add(detail);
//                            }
//
//                            TextView tvCharge =
//                                    CartActivity.this.findViewById(R.id.txv_delivery_charge_price);
//                            TextView tvDiscount =
//                                    CartActivity.this.findViewById(R.id.txv_discount_price);
//                            TextView tvTotal = CartActivity.this.findViewById(R.id.txv_total_price);
//
//
//                            int totalPrice = 0;
//                            totalPrice = parsePrice(tvTotal.getText().toString()) +
//                                    parsePrice(tvCharge.getText().toString()) -
//                                    parsePrice(tvDiscount.getText().toString());
////                            updateDate.put("detail", detailUpdateDate);
////                            updateDate.put("totalPrice", totalPrice);
////                            updateDate.put("restaurant", "");
////                            updateDate.put("username", "UNIQUE_USER_ID");
//
//                            UserOrder userOrder = new UserOrder(
//                                    "UNIQUE_USER_ID", "Mcdonald", totalPrice,
//                                    true,true, detailUpdateDate);
//
//
//                            userCart.child("UNIQUE_USER_ID")
//                                    .setValue(userOrder);
//                        }
//                        else
//                        {
////                            Map<String,Object> updateDate = new HashMap<>();
//
//                            List<Object> detailUpdateDate = new ArrayList<>();
//                            for(CartItem cartItem:mAdapter.cartItems){
//                                Map<String,Object> detail = new HashMap<>();
//                                detail.put("name",cartItem.getName());
//                                detail.put("price",cartItem.getPrice());
//                                detail.put("amounts",cartItem.getAmount());
//                                detailUpdateDate.add(detail);
//                            }
//
//
//                            TextView tvCharge = CartActivity.this.findViewById(R.id.txv_delivery_charge_price);
//
//                            TextView tvDiscount = CartActivity.this.findViewById(R.id.txv_discount_price);
//
//                            TextView tvTotal = CartActivity.this.findViewById(R.id.txv_total_price);
//
//
//                            int totalPrice = 0;
//                            totalPrice = parsePrice(tvTotal.getText().toString()) +
//                                    parsePrice(tvCharge.getText().toString()) -
//                                    parsePrice(tvDiscount.getText().toString());
////                            updateDate.put("detail", detailUpdateDate);
////                            updateDate.put("totalPrice", totalPrice);
////                            updateDate.put("restaurant", "");
////                            updateDate.put("username", "UNIQUE_USER_ID");
//                            UserOrder userOrder = new UserOrder(
//                                    "UNIQUE_USER_ID", "Mcdonald", totalPrice,
//                                    true,true, detailUpdateDate);
//                            userCart.child("UNIQUE_USER_ID")
//                                    .setValue(userOrder);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//        Intent intent = new Intent(this, OrderStatusActivity.class);
//        startActivity(intent);
//        finish();
    }

    private int parsePrice(String s){
        Pattern p = Pattern.compile("\\d+");
        String priceStr = s;
        Matcher m = p.matcher(priceStr);
        int price = 0;
        while(m.find()) {
            price = Integer.parseInt(m.group());
        }
        return price;
    }

    @Override
    public void onDrinkLoadSuccess(List<CartItem> cartItemList) {
//        TextView t = this.findViewById(R.id.textView7);
//        t.setText(cartItemList.get(0).getName());
//        mAdapter = new CartItemsDataAdapter(this,cartItemList);
//        setupRecyclerView();
    }

    @Override
    public void onDrinkLoadFailed(String message) {

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
}