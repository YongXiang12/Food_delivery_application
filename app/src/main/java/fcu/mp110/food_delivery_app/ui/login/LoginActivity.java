package fcu.mp110.food_delivery_app.ui.login;

import static android.content.ContentValues.TAG;

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

import fcu.mp110.food_delivery_app.MainActivity;
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
    static public String Login_detail;

    public void onLogin(View view){
        String email = etEmail.getText().toString();
        Login_detail = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( this, (OnCompleteListener<AuthResult>) this);
        Log.d(TAG, "Testing login_account: " +email+", "+Login_detail);

//        View headerView = navigationView.getHeaderView(0);
//        TextView drawer_name = (TextView) headerView.findViewById(R.id.header_txv_name);
//        TextView drawer_email = (TextView) headerView.findViewById(R.id.header_txv_email);
//        LoginActivity ts2 = new LoginActivity() ;
//        Log.d(TAG, "Login_Detail: " + email + ", "+ts2.Login_detail);//(ts1.Login_detail)
//
//        drawer_name.setText("User");
//        drawer_email.setText(email);
//        String Login_detail = email;
    }
    public void onCancel(View view){
        finish();
    }

    static public int Success_Login;

    @Override
    public void onComplete(@NonNull Task task) {

        if (task.isSuccessful()){
            Toast.makeText(this,"Successful", Toast.LENGTH_LONG).show();

            Success_Login=1;

            Log.d(TAG, "Testing log: " +Success_Login);

//            finish();
            Intent back = new Intent();
            back.setClass(this, MainActivity.class);
            startActivity(back);
            //addUser();
        } else {
            Success_Login=0;
            Toast.makeText(this,"Fail", Toast.LENGTH_LONG).show();
        }
    }



}