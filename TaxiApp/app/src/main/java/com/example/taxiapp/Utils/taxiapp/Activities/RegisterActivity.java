package com.example.taxiapp.Utils.taxiapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taxiapp.R;
import com.example.taxiapp.Utils.taxiapp.Utils.MyButton;


public class RegisterActivity extends AppCompatActivity {
    MyButton btnsignup,btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnsignup=(MyButton)findViewById(R.id.btnSignupSubmit);
        btncancel=(MyButton)findViewById(R.id.btnSignupCancel);

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                finish();
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
        });
    }
}
