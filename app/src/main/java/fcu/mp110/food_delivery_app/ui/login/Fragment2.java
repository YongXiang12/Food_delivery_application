package fcu.mp110.food_delivery_app.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import fcu.mp110.food_delivery_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class Fragment2 extends Fragment{

    private EditText etEmail;
    private EditText etPassword;
    private EditText etAccountName;

    private FirebaseAuth firebaseAuth;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       //etEmail = getView().findViewById(R.id.register_Email);
        //etPassword = getView().findViewById(R.id.register_Password);
        //etAccountName = getView().findViewById(R.id.register_Account);

        //firebaseAuth = FirebaseAuth.getInstance();

        return inflater.inflate(R.layout.fragment_2, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

//        etEmail = view.findViewById(R.id.register_Email);
//        etPassword =view.findViewById(R.id.register_Password);
//        etAccountName =view.findViewById(R.id.register_Account);

//        firebaseAuth = FirebaseAuth.getInstance();

//    }

//    public void onRegister(View view) {
//        String email = etEmail.getText().toString();
//        String password = etPassword.getText().toString();
//
//        Log.v("MyApp", email);
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener((Executor) this,this);
//    }

//    @Override
//    public void onComplete(@NonNull Task task) {
//        if (task.isSuccessful()){
//            //Toast.makeText(this,"Successful", Toast.LENGTH_LONG).show();
//            //addUser();
//        } else {
//            //Toast.makeText(this,"Fail", Toast.LENGTH_LONG).show();
//        }
//    }

}