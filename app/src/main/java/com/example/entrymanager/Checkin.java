package com.example.entrymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Checkin extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference dr;
    private static final int PERMISSION_SEND_SMS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        database = FirebaseDatabase.getInstance();
        dr = database.getReference();
        //on clo
        requestSmsPermission();

//        dr.child("Visitor").child(vname.getText().toString()).child("Phone").setValue(vphone.getText().toString());
//        dr.child("Visitor").child(vname.getText().toString()).child("Email").setValue(vmail.getText().toString());
//        dr.child("Visitor").child(vname.getText().toString()).child("CheckIn").setValue(intime.getText().toString());
//        dr.child("Visitor").child(vname.getText().toString()).child("CheckOut").setValue(outtime.getText().toString());
//
//        dr.child("Host").child(vname.getText().toString()).child("Name").setValue(hname.getText().toString());
//        dr.child("Host").child(vname.getText().toString()).child("Phone").setValue(hphone.getText().toString());
//        dr.child("Host").child(vname.getText().toString()).child("Email").setValue(hmail.getText().toString());
    }
    public void goCheckIn(View view) {

        EditText vname=findViewById(R.id.vname);
        EditText vphone=findViewById(R.id.vphone);

        EditText vmail=findViewById(R.id.vmail);
        EditText intime = findViewById(R.id.intime);
        EditText outtime=findViewById(R.id.outtime);
        EditText hname=findViewById(R.id.hname);
        EditText hphone=findViewById(R.id.hphone);
        EditText hmail=findViewById(R.id.hmail);
        dr.child("Visitor").child(vphone.getText().toString()).child("Name").setValue(vname.getText().toString());
        dr.child("Visitor").child(vphone.getText().toString()).child("Mail").setValue(vmail.getText().toString());
        dr.child("Visitor").child(vphone.getText().toString()).child("CheckIn").setValue(intime.getText().toString());
        dr.child("Visitor").child(vphone.getText().toString()).child("CheckOut").setValue(outtime.getText().toString());
        dr.child("Visitor").child(vphone.getText().toString()).child("Host Name").setValue(hname.getText().toString());

        dr.child("Host").child(hname.getText().toString()).child("Name").setValue(hname.getText().toString());
        dr.child("Host").child(hname.getText().toString()).child("Phone").setValue(hphone.getText().toString());
        dr.child("Host").child(hname.getText().toString()).child("Email").setValue(hmail.getText().toString());


        String name=vname.getText().toString();
        String phone=vphone.getText().toString();
        String visitormail=vmail.getText().toString();
        String in=intime.getText().toString();
        String out=outtime.getText().toString();
        String hostmail=hmail.getText().toString();
        String hostphone=hphone.getText().toString();


        String message="Here are the Visitor details : \n"+"Name - "+name+"\nEmail - "+visitormail+"\nPhone - "+phone+"\nCheckIn Time - "+in+"\nTentative Check-out Time - "+out ;

        JavaMailAPI mailToHost = new JavaMailAPI(this, hostmail, "You have a visitor: " +name , message);

        mailToHost.execute();



        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(hostphone, null, message, pi, null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();
    }


    private void requestSmsPermission() {

        // check permission is given
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(Checkin.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_SEND_SMS);
        } else {

        }
    }
    }


