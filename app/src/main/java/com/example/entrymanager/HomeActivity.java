package com.example.entrymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void checkin(View view){
        Intent intent =new Intent(this,Checkin.class);
        startActivity(intent);
    }



    public void checkout(View view) {
        Intent intent =new Intent(this,Checkout.class);
        startActivity(intent);
    }
}
