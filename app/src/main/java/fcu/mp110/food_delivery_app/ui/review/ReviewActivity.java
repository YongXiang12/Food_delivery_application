package fcu.mp110.food_delivery_app.ui.review;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fcu.mp110.food_delivery_app.R;
import java.util.Calendar;
import fcu.mp110.food_delivery_app.ui.login.LoginActivity;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantMenu;



public class ReviewActivity extends AppCompatActivity {

    Context context;
    private ListView lvReview;
    private TextView txv1,txv2;
    private ReviewAdapter adapter;
    private int x_last=0;
    private String x_select;
    private Button btn1;
    private String review_name;
    Date currentTime = Calendar.getInstance().getTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        RestaurantMenu rst1 = new RestaurantMenu();
        Log.d(TAG, "restaurant : " + rst1.review_restaurant);
        String review_rest = rst1.review_restaurant;

        LoginActivity ts1 = new LoginActivity() ;
        Log.d(TAG, "Success_Login: " + ts1.Success_Login);
        txv1 = (TextView)findViewById(R.id.editTextTextPersonName);
        btn1 = (Button)findViewById(R.id.btn_writeReview);
        lvReview = findViewById(R.id.lv_review);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("review");

        DatabaseReference myRef2 = myRef.child(review_rest);

//        DatabaseReference getID = database.getReference("users");

        review_name = (ts1.Login_detail);
        Log.d(TAG, "Login_Detail: " + review_name);//(ts1.Login_detail)
//        DatabaseReference getID2 = getID.child("");

//        firebase_select(myRef2);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                //ts1.getSuccess();
                Log.d(TAG, "Success_Login: " + ts1.Success_Login);
                if(ts1.Success_Login==1){
                    Map<String,Object> updateDate = new HashMap<>();
                    updateDate.put("imgResId", "https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-d2126.appspot.com/o/restaurant%2Fdamino's.jpg?alt=media&token=a90d5a77-4b3b-43cf-a69f-2d132fdd66a4");
                    updateDate.put("userName", ts1.Login_detail);
                    String[] cut= (currentTime.toString()).split(" ");
                    Log.d(TAG, "cut: " + cut[1]+" "+cut[2]+ " "+cut[5]);

                    updateDate.put("date", cut[5]+"/"+cut[1]+"/"+cut[2]);
                    updateDate.put("review", txv1.getText().toString());
//                myRef2.addChildEventListener(updateDate);
                    myRef2.push().setValue(updateDate);
                }
//                else{
//                    Toast tos = Toast.makeText(ReviewActivity,"Please Login",Toast.LENGTH_LONG);
//                    tos.show();
////                    View parentLayout = findViewById(android.R.id.activity_review);
////                    Snackbar snackbar = Snackbar
////                            .make(parentLayout, "Please Login", Snackbar.LENGTH_LONG);
////                    snackbar.show();
//                }


//
            }
        });
        if(ts1.Success_Login==0){
            Toast tos = Toast.makeText(this,"Please Login",Toast.LENGTH_LONG);
            tos.show();
        }
//        firebase_select(myRef2);
        ArrayList<ReviewItem> reviewItemArrayList = new ArrayList<ReviewItem>();
        ChildEventListener childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                ReviewItem user_data = dataSnapshot.getValue(ReviewItem.class);
                reviewItemArrayList.add(user_data);

                // A new comment has been added, add it to the displayed list
//                Comment comment = dataSnapshot.getValue(Comment.class);

                // ...
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef2.addChildEventListener(childEventListener);
        adapter = new ReviewAdapter(this, R.layout.review_listitem, reviewItemArrayList);
//            adapter.notifyDataSetChanged();
        lvReview.setAdapter(adapter);

//        ArrayList<ReviewItem> reviewItemArrayList = new ArrayList<ReviewItem>();
//        reviewItemArrayList.add(new ReviewItem("R.drawable.arrow_left","Jason",
//                "2020/5/5","早上好中國"));
//        reviewItemArrayList.add(new ReviewItem("R.drawable.pizza","Ricky",
//                "2020/5/3","冰淇淋"));
//        reviewItemArrayList.add(new ReviewItem("R.drawable.toolbar_user","maxchan",
//                "2020/5/4","我在喜歡"));
//        reviewItemArrayList.add(new ReviewItem(R.drawable.toolbar_user,"Jason",
//                "2020/5/5","早上好中國"));
//        reviewItemArrayList.add(new ReviewItem(R.drawable.toolbar_user,"Jason",
//                "2020/5/5","早上好中國"));
//        adapter = new ReviewAdapter(this, R.layout.review_listitem, reviewItemArrayList);
//
//        lvReview.setAdapter(adapter);
    }

    private void firebase_select(DatabaseReference db) {
        lvReview.setAdapter(null);
        ArrayList<ReviewItem> reviewItemArrayList = new ArrayList<ReviewItem>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    ReviewItem user_data = ds.getValue(ReviewItem.class);
                    reviewItemArrayList.add(user_data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
//        if(!reviewItemArrayList.isEmpty()) {
            adapter = new ReviewAdapter(this, R.layout.review_listitem, reviewItemArrayList);
//            adapter.notifyDataSetChanged();
            lvReview.setAdapter(adapter);
//        }

    }

    public void goBack(View view) {
        finish();
    }
}