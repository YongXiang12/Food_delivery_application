package fcu.mp110.food_delivery_app.ui.review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fcu.mp110.food_delivery_app.R;

public class ReviewActivity extends AppCompatActivity {

    Context context;
    private ListView lvReview;
    private TextView txv1,txv2;
    private ReviewAdapter adapter;
    private int x_last=0;
    private String x_select;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        txv1 = (TextView)findViewById(R.id.editTextTextPersonName);
        btn1 = (Button)findViewById(R.id.btn_writeReview);
        lvReview = findViewById(R.id.lv_review);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("review");

        DatabaseReference myRef2 = myRef.child("review_get");

        firebase_select(myRef2);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                x_last+=1;

                Map<String,Object> updateDate = new HashMap<>();
                updateDate.put("imgResId", "https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-d2126.appspot.com/o/restaurant%2Fdamino's.jpg?alt=media&token=a90d5a77-4b3b-43cf-a69f-2d132fdd66a4");
                updateDate.put("userName", "UNIQUE_USER_ID");
                updateDate.put("date", "2022/6/6");
                updateDate.put("review", txv1.getText().toString());
//                myRef2.addChildEventListener(updateDate);
                myRef2.push().setValue(updateDate);
//                firebase_select(myRef2);
            }
        });
//        lvReview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ImageView img = (ImageView) view.findViewById(R.id.picture_review);
//
//                TextView name = (TextView) view.findViewById(R.id.name_review);
//                name.getText().toString();
//
//            }
//        });


//        ArrayList<ReviewItem> reviewItemArrayList = new ArrayList<ReviewItem>();
//        reviewItemArrayList.add(new ReviewItem("R.drawable.arrow_left","Jason",
//                "2020/5/5","早上好中國"));
//        reviewItemArrayList.add(new ReviewItem("R.drawable.pizza","Ricky",
//                "2020/5/3","冰淇淋"));
//        reviewItemArrayList.add(new ReviewItem("R.drawable.toolbar_user","maxchan",
//                "2020/5/4","我在喜歡"));
////        reviewItemArrayList.add(new ReviewItem(R.drawable.toolbar_user,"Jason",
////                "2020/5/5","早上好中國"));
////        reviewItemArrayList.add(new ReviewItem(R.drawable.toolbar_user,"Jason",
////                "2020/5/5","早上好中國"));
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
//                int uhfu = 9;
                for (DataSnapshot ds:snapshot.getChildren()){
                    ReviewItem user_data = ds.getValue(ReviewItem.class);
                    reviewItemArrayList.add(user_data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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