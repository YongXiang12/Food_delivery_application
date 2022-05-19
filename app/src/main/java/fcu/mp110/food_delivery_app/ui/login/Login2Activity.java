package fcu.mp110.food_delivery_app.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import fcu.mp110.food_delivery_app.R;
//REGISTER
public class Login2Activity extends AppCompatActivity implements OnCompleteListener<AuthResult> {
    private EditText etEmail;
    private EditText etPassword;
    private EditText etAccountName;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_register_new);

         etEmail = findViewById(R.id.register_Email);
         etPassword = findViewById(R.id.register_Password);
         etAccountName = findViewById(R.id.register_Account);

    }
    public void onRegister(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Log.v("MyApp", (email));
        Log.v("MyApp", (password));
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( this, (OnCompleteListener<AuthResult>) this);
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

    public void goback(View v){
        finish();
    }
    public void onCancel(View view){
        finish();
    }

    private void addUser() {
        String email = etEmail.getText().toString();
        String account = etAccountName.getText().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userRef = firebaseDatabase.getReference("users");
        DatabaseReference accountRef = userRef.child(account);
        Map<String, Object> user = new HashMap<>();
        user.put("email",email);
        user.put("account",account);
        accountRef.updateChildren(user);
    }
}
