package fcu.mp110.food_delivery_app.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fcu.mp110.food_delivery_app.R;

public class tst extends AppCompatActivity {
    private TextView txt;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmp);
        txt = findViewById(R.id.textView16);
    }

    public void teting(View view){

        txt.setText("helloooooooooooooooo ");
    }


}
