package com.example.taxiapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.taxiapp.R;

public class DriverActivity extends AppCompatActivity implements View.OnClickListener {

    Button requestOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        requestOk = (Button) findViewById(R.id.btn_ok);
        requestOk.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_ok:
                System.out.println("aaaa");
                Intent intent = new Intent(getApplicationContext(), PaymentsActivity.class);
                startActivity(intent);
                finish();
            case 3:
                break;
        }
    }

}
