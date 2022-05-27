package fcu.mp110.food_delivery_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fcu.mp110.food_delivery_app.databinding.ActivityMainBinding;
import fcu.mp110.food_delivery_app.ui.restaurant.CategoriesArrayAdapter;
import fcu.mp110.food_delivery_app.ui.restaurant.CategoriesItem;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantArrayAdapter;
import fcu.mp110.food_delivery_app.ui.restaurant.RestaurantItem;
import fcu.mp110.food_delivery_app.ui.search.SearchPage;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_login)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        tb.setNavigationIcon(R.drawable.ic_option);*/

        //測試改變右上個人圖片
        /*ImageView pro_img = (ImageView) findViewById(R.id.toolbar_pro_img);
        String uri = "@drawable/" + "ic_menu_camera";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable image = getResources().getDrawable(imageResource);
        pro_img.setImageDrawable(image);*/

        //測試改變左邊選單個人資料
        View headerView = navigationView.getHeaderView(0);
        TextView drawer_name = (TextView) headerView.findViewById(R.id.header_txv_name);
        TextView drawer_email = (TextView) headerView.findViewById(R.id.header_txv_email);
        drawer_name.setText("Alan");
        drawer_email.setText("aa@gmail.com");


        // Search TextField init
        TextView Search_TextField = findViewById(R.id.Search_TextField);
        Search_TextField.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, SearchPage.class);
                startActivity(it);
            }
        });

        //FeaturedRestaurant 可右滑recyclerview
        initFeaturedRestaurant();
        initCategories();
    }
    public void initCategories(){
        RecyclerView recyclerViewCategories = this.findViewById(R.id.recyclerview_categories);
        ArrayList<CategoriesItem> categoriesList = new ArrayList<CategoriesItem>();
        categoriesList.add(new CategoriesItem(R.drawable.pizza , "Pizza"));
        categoriesList.add(new CategoriesItem(R.drawable.ic_baseline_coffee_24 , "Coffee"));
        categoriesList.add(new CategoriesItem(R.drawable.ic_baseline_fastfood_24 , "Fastfood"));
        categoriesList.add(new CategoriesItem(R.drawable.pizza , "pizza"));
        categoriesList.add(new CategoriesItem(R.drawable.pizza , "pizza"));
        CategoriesArrayAdapter adapter = new CategoriesArrayAdapter(this, categoriesList);

        recyclerViewCategories.setAdapter(adapter);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }
    public void initFeaturedRestaurant() {
        //setOnClickListener();
        RecyclerView recyclerViewStore = this.findViewById(R.id.store_recyclerview);
        ArrayList<RestaurantItem> restaurantList = new ArrayList<RestaurantItem>();
        RestaurantArrayAdapter adapter = new RestaurantArrayAdapter(MainActivity.this, restaurantList);
        recyclerViewStore.setAdapter(adapter);
        recyclerViewStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        DatabaseReference restaurantRef;
        restaurantRef = FirebaseDatabase.getInstance()
                .getReference("Restaurant");
        restaurantRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        RestaurantItem restaurantItem = dataSnapshot.getValue(RestaurantItem.class);
                        restaurantItem.setKey(dataSnapshot.getKey());
                        
                        restaurantList.add(restaurantItem);

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
//        RecyclerView recyclerViewStore = this.findViewById(R.id.store_recyclerview);
//        ArrayList<StoreItem> storeList = new ArrayList<StoreItem>();
//        storeList.add(new StoreItem(R.drawable.ic_mcdonald, "5.0", "A", "麥當勞", "hamburger"));
//        storeList.add(new StoreItem(R.drawable.hawwaipizza, "5.0", "B", "達美樂", "pizza"));
//        storeList.add(new StoreItem(R.drawable.seafood_pizza, "5.0", "B", "BB", "hamburger"));
//        storeList.add(new StoreItem(R.drawable.seafood_pizza, "5.0", "B", "BB", "hamburger"));
//        storeList.add(new StoreItem(R.drawable.seafood_pizza, "5.0", "B", "BB", "hamburger"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}