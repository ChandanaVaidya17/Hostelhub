package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Collection;

public class AdminPage extends AppCompatActivity {

    EditText msg;
    Button rgstStd, post, logout;
    TextView bfast, lunch, dinner, outStation,rqst;

    FirebaseAuth mAuth;
    DatabaseReference dbRefere ,dbReferr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        msg = findViewById(R.id.Message);
        rgstStd = findViewById(R.id.register);
        post = findViewById(R.id.messagePostBtn);
        logout = findViewById(R.id.logout);
        bfast = findViewById(R.id.Breakfast);
        lunch = findViewById(R.id.Lunch);
        dinner = findViewById(R.id.Dinner);
//        outStation =  findViewById(R.id.outstation);
        rqst = findViewById(R.id.request);




        rgstStd.setOnClickListener(view -> {
            startActivity(new Intent(AdminPage.this, Register.class));
        });

        logout.setOnClickListener(view -> {
            startActivity(new Intent(AdminPage.this, MainActivity.class));
            finish();
        });

      dbRefere = FirebaseDatabase.getInstance().getReference("Notice");

      post.setOnClickListener(view -> {
          String message = msg.getText().toString();
          dbRefere.setValue(message);
          Toast.makeText(getApplicationContext(), "sucessfully posted", Toast.LENGTH_LONG).show();
      });

        dbReferr = FirebaseDatabase.getInstance().getReference();
        dbReferr.child("Request").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    String stringtoconvert = String.valueOf(snapshot.getValue());
                    rqst.setText("Request: "+stringtoconvert);
                }catch (Exception e){
                    Log.i("Error excpt","onDataNotice");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReferr.child("Breakfast").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    bfast.setText("Breakfast : "+ Integer.parseInt((String.valueOf(snapshot.getValue()))));
                }catch (Exception e){
                    Log.i("Error excpt","onDataNotice");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReferr.child("Lunch").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    lunch.setText("Lunch : "+ Integer.parseInt((String.valueOf(snapshot.getValue()))));
                }catch (Exception e){
                    Log.i("Error excpt","onDataNotice");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReferr.child("Dinner").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    dinner.setText("Lunch : "+ Integer.parseInt((String.valueOf(snapshot.getValue()))));
                }catch (Exception e){
                    Log.i("Error excpt","onDataNotice");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbReferr.child("Outstation").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    outStation.setText("Lunch : "+ Integer.parseInt((String.valueOf(snapshot.getValue()))));
                }catch (Exception e){
                    Log.i("Error excpt","onDataNotice");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminPage.this, MainActivity.class));
        finish();

    }
}