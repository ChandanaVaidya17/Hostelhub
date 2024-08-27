package com.example.myhostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mAuth = FirebaseAuth.getInstance();
    }

    public void admin(View view) {
        startActivity(new Intent(this, admin_login.class));

    }

    public void student(View view) {
        startActivity(new Intent(this, student_login.class));

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null){
//            startActivity(new Intent(MainActivity.this, ));
//        }
//    }


    public void aboutus(View v){
        Intent i = new Intent(MainActivity.this,aboutus.class);
        startActivity(i);

    }


}