package fcu.mp110.food_delivery_app.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import fcu.mp110.food_delivery_app.MainActivity;
import fcu.mp110.food_delivery_app.R;

public class LoginActivity extends AppCompatActivity implements OnCompleteListener<AuthResult> {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private EditText etEmail;
    private EditText etPassword;
    private EditText etAccountName;

    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);



        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Fragment1(), "Login");
        vpAdapter.addFragment(new Fragment2(), "Register");

        viewPager.setAdapter(vpAdapter);
        etEmail = findViewById(R.id.register_Email);
        etPassword = findViewById(R.id.register_Password);
        etAccountName = findViewById(R.id.register_Account);

    }

    public void onRegister(View view) {
        String email = ((EditText)findViewById(R.id.register_Email)).getText().toString();
        String password = ((EditText)findViewById(R.id.register_Password)).getText().toString();

        //String email = etEmail.getText().toString();
        //String password = etPassword.getText().toString();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        Log.v("MyApp", (email));
        Log.v("MyApp", (password));
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( this,this);
    }
    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()){
            Toast.makeText(this,"Successful", Toast.LENGTH_LONG).show();
            addUser();
        } else {
            Toast.makeText(this,"Fail", Toast.LENGTH_LONG).show();
        }
    }

    private void addUser() {
        String email = ((EditText)findViewById(R.id.register_Email)).getText().toString();
        String account = ((EditText)findViewById(R.id.register_Account)).getText().toString();
//        Log.v("MyApp_ADD", (email));
//        Log.v("MyApp_ADD", (account));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userRef = firebaseDatabase.getReference("users");
        DatabaseReference accountRef = userRef.child(account);
        Map<String, Object> user = new HashMap<>();
        user.put("email",email);
        user.put("account",account);
        accountRef.updateChildren(user);
    }
}