package fcu.mp110.food_delivery_app.ui.review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.R;

public class ReviewActivity extends AppCompatActivity {

    private ListView lvReview;
    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ArrayList<ReviewItem> reviewItemArrayList = new ArrayList<ReviewItem>();
        reviewItemArrayList.add(new ReviewItem(R.drawable.arrow_left,"Jason",
                "2020/5/5","早上好中國"));
        reviewItemArrayList.add(new ReviewItem(R.drawable.pizza,"Ricky",
                "2020/5/3","冰淇淋"));
        reviewItemArrayList.add(new ReviewItem(R.drawable.toolbar_user,"maxchan",
                "2020/5/4","我在喜歡"));
        reviewItemArrayList.add(new ReviewItem(R.drawable.toolbar_user,"Jason",
                "2020/5/5","早上好中國"));
        reviewItemArrayList.add(new ReviewItem(R.drawable.toolbar_user,"Jason",
                "2020/5/5","早上好中國"));
        adapter = new ReviewAdapter(this, R.layout.review_listitem, reviewItemArrayList);
        lvReview = findViewById(R.id.lv_review);
        lvReview.setAdapter(adapter);
    }

    public void goBack(View view) {
        finish();
    }
}