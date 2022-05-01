package fcu.mp110.food_delivery_app.ui.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fcu.mp110.food_delivery_app.MainActivity;
import fcu.mp110.food_delivery_app.R;

public class SearchPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);
    }

    public void search(View view){
        Intent it = new Intent(this, SearchResultPage.class);
        startActivity(it);
    }

}