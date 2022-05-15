package fcu.mp110.food_delivery_app.ui.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import fcu.mp110.food_delivery_app.R;


public class OrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(50);
    }
    public void goBack(View view)
    {
        finish();
    }
}