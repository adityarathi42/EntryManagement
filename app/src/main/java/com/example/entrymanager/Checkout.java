package com.example.entrymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Checkout extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference dr;


    String intime;
    String mail;
    String hname;
    String outtime;
    String name;
    String p;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        this.getSupportActionBar().setTitle("Check-out");
        database = FirebaseDatabase.getInstance();
        dr = database.getReference();

        EditText vname = findViewById(R.id.vname2);



        vname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                EditText vphone = findViewById(R.id.vphone2);

                p=vphone.getText().toString();
                dr=database.getReference().child("Visitor").child(p).child("Mail");
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue(String.class);
                        mail=value;

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
                //HOST NAME
                dr=database.getReference().child("Visitor").child(p).child("Host Name");
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue(String.class);
                        hname=value;

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

                //Check in time
                dr=database.getReference().child("Visitor").child(p).child("CheckIn");
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue(String.class);
                        intime=value;

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

                //Name
                dr=database.getReference().child("Visitor").child(p).child("Name");
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = dataSnapshot.getValue(String.class);
                        name=value;

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });



                return false;
            }
        });

    }

    public void goCheckOut(View view) {

        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String message="You have been checked out! Here are the visit details: "+"\nName - "+name+"\nEmail - "+mail+"\nPhone - "+p+"\nHost Name - "+hname+"\nCheckIn Time - "+intime+"\nCheck-out Time - "+currentTime ;
    JavaMailAPI mailToHost = new JavaMailAPI(this, mail, "Visit Details " , message);

        mailToHost.execute();
    }
}
