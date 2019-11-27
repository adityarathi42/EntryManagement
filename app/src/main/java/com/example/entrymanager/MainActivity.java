package com.example.entrymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signup(View view){
        Intent intent =new Intent(this,SignUp.class);
        startActivity(intent);
    }
    public void guest(View view){
        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
    }
}
