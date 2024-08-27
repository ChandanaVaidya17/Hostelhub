package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {

    EditText userName, Password;
    Button login;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        userName = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            loginUser();

        });


    }

    private void loginUser() {
        String email = userName.getText().toString();
        String password = Password.getText().toString();

        if(TextUtils.isEmpty(email)){
            userName.setError("Email cannot be empty");
            userName.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            Password.setError("Password cannot be empty");
            Password.requestFocus();
        }else{

        if(TextUtils.equals(email, "chandana.20cs018@sode-edu.in")  && TextUtils.equals(password, "admin@123456")){
            Toast.makeText(admin_login.this, "Login successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(admin_login.this, AdminPage.class));

        }else{
            Toast.makeText(admin_login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
            Toast.makeText(admin_login.this, "Please ask the Developer to register yourself", Toast.LENGTH_SHORT).show();
            finish();
        }

        }
    }
}