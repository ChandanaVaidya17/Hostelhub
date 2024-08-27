package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {
    EditText name, emil, pass, phno;
    Button regster;

    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        emil = findViewById(R.id.eml);
        pass = findViewById(R.id.password);
        regster = findViewById(R.id.rst);
        phno = findViewById(R.id.ph);
        mAuth = FirebaseAuth.getInstance();

        regster.setOnClickListener(view -> {
            createUser();
            addDetails();

        });


    }

    private void addDetails() {
        String Name = name.getText().toString();
        String ph = phno.getText().toString();
        String email = emil.getText().toString();

        if(TextUtils.isEmpty(email)){
            emil.setError("Invalid field");
            emil.requestFocus();
        } else if (TextUtils.isEmpty(ph)) {
            phno.setError("Invalid field");
            phno.requestFocus();
        } else if (TextUtils.isEmpty(Name)) {
            name.setError("Cannot be empty");
            name.requestFocus();
        }else {

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            DatabaseReference chdref = ref.child("Users");
            DatabaseReference emailref = chdref.child(ph);

            emailref.child("name").setValue(Name);
            emailref.child("Email").setValue(email);
            Toast.makeText(Register.this, "Added other fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void createUser() {
        String email = emil.getText().toString();
        String password = pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            emil.setError("Invalid field");
            emil.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            pass.setError("Invalid field");
            pass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this, "Added student", Toast.LENGTH_SHORT).show();
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fstore.collection("user").document(userID);

                    }else{
                        Toast.makeText(Register.this, "Failed to add  student", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }


    }


}