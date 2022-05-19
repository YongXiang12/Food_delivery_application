package fcu.mp110.food_delivery_app.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import fcu.mp110.food_delivery_app.R;
//LOGIN
public class LoginActivity extends AppCompatActivity  implements OnCompleteListener<AuthResult>{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private EditText etEmail;
    private EditText etPassword;
    private EditText etAccountName;
    private TextView txt;
    private Button btn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        etEmail = findViewById(R.id.Email);
        etPassword = findViewById(R.id.Password);


    }


    public void gotoRegister(View v){
        Intent it = new Intent();
        it.setClass(this, Login2Activity.class);
        startActivity(it);
    }

    public void onLogin(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( this, (OnCompleteListener<AuthResult>) this);
    }
    public void onCancel(View view){
        finish();
    }
    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()){
            Toast.makeText(this,"Successful", Toast.LENGTH_LONG).show();
            finish();
            //addUser();
        } else {
            Toast.makeText(this,"Fail", Toast.LENGTH_LONG).show();
        }
    }

}