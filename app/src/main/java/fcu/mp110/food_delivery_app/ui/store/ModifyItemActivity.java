package fcu.mp110.food_delivery_app.ui.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fcu.mp110.food_delivery_app.R;



public class ModifyItemActivity extends AppCompatActivity {

    RecyclerView recyclerView ;

    String[] food_name = new String[]{
            "SeaFood" ,
            "Hawwai"
    };

    String price[]  = {"70","80"};
    int image[]  = {R.drawable.seafood_pizza , R.drawable.hawwaipizza};

    List<ModifyItems> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_item);

        //ModifyItems m = new ModifyItems("SeaFood" ,R.drawable.seafood_pizza ,"70" );

        lists.add(new ModifyItems("SeaFood" ,R.drawable.seafood_pizza ,"70" , 10));
        lists.add(new ModifyItems("hawwai" ,R.drawable.hawwaipizza,"70" ,20 ));


        recyclerView = findViewById(R.id.recycler);

        ModifyAdapt adapt = new ModifyAdapt(this,lists);
        recyclerView.setAdapter(adapt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public void AddNewItems(View view){
        Intent intent = new Intent(this , AddNewItems.class);
        startActivity(intent);
    }


}