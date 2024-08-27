package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.spec.ECField;

public class Studentpage extends AppCompatActivity {
    Button btn, Post;
    DatabaseReference databaseReference;
    TextView Nmsg,notice, stdMsg, uname;
    FirebaseAuth mAuth;
    FirebaseUser user;
    RadioButton brkfst, lnch, dnr;
    Switch otstn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentpage);

        btn = findViewById(R.id.logout);
        notice = findViewById(R.id.nts);
        Post = findViewById(R.id.post);
        stdMsg = findViewById(R.id.sm);
        uname = findViewById(R.id.username);
        brkfst = findViewById(R.id.breakfast);
        lnch = findViewById(R.id.lunch);
        dnr = findViewById(R.id.dinner);
//        otstn = findViewById(R.id.outstation);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }

        else{
            uname.setText( "Name: "+ user.getEmail());
        }



        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Notice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    String stringtoconvert = String.valueOf(snapshot.getValue());
                    notice.setText("admin says: " +  stringtoconvert);
                }catch (Exception e){
                    Log.i("Error excpt","onDataNotice");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        FirebaseUser currentuser = mAuth.getCurrentUser();
//
//        if(currentuser != null){
//            String userEmail = currentuser.getEmail();
//            uname.setText(userEmail);
//        }
//


        Post.setOnClickListener(view -> {
            databaseReference = FirebaseDatabase.getInstance().getReference("Request");
            String message = stdMsg.getText().toString();

            databaseReference.setValue(message);
            Toast.makeText(getApplicationContext(), "sucessfully posted", Toast.LENGTH_LONG).show();
        });


        btn.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(Studentpage.this, MainActivity.class));
            finish();
        });



//
//        databaseReference.child("Breakfast").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                try {
//                    if(brkfst.isChecked()) {
//                        bf = Integer.parseInt(String.valueOf(snapshot.getValue()));
//                        bf += 1;
//                        databaseReference.setValue(bf);
//                    }
//                }catch (Exception e){
//                    Log.i("Error excpt" , "onDataNotice berakfast");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        databaseReference = FirebaseDatabase.getInstance().getReference();

        brkfst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brkfst.isChecked()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Breakfast");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                int currentValue = snapshot.getValue(Integer.class); // Assumes the value in the database is an integer

                                int updatedValue = currentValue + 1;
                                databaseReference.setValue(updatedValue);
                                boolean checkedTrue = brkfst.isChecked() ? true : false;

                                brkfst.setEnabled(! checkedTrue);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        brkfst.setEnabled(true);
                                    }
                                }, 4 * 60 * 60 * 1000);

                            } catch (Exception e) {
                                Log.e("Error", "Failed to update value in the database: " + e.getMessage());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Error", "Database operation canceled: " + error.getMessage());
                        }
                    });
                } else {
                    // Checkbox is already checked, clear the check
                    brkfst.setChecked(false);
                }
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference();

        lnch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lnch.isChecked()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Outstation");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {

                                int currentValue = snapshot.getValue(Integer.class); // Assumes the value in the database is an integer
                                int updatedValue = currentValue + 1;
                                databaseReference.setValue(updatedValue);
                                boolean checkedTrue = lnch.isChecked() ? true : false;

                                lnch.setEnabled(! checkedTrue);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        lnch.setEnabled(true);
                                    }
                                }, 4 * 60 * 60 * 1000);

                            } catch(
                        Exception e)

                        {
                            Log.e("Error", "Failed to update value in the database: " + e.getMessage());
                        }
                    }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Error", "Database operation canceled: " + error.getMessage());
                        }
                    });
                }
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();

        dnr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (dnr.isChecked()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Dinner");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {

                                int dn = snapshot.getValue(Integer.class); // Assumes the value in the database is an integer
                                dn += 1;
                                databaseReference.setValue(dn);
                                boolean checkedTrue = dnr.isChecked() ? true : false;

                                dnr.setEnabled(! checkedTrue);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dnr.setEnabled(true);
                                    }
                                }, 4 * 60 * 60 * 1000);

                            } catch (
                                    Exception e) {
                                Log.e("Error", "Failed to update value in the database: " + e.getMessage());
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Error", "Database operation canceled: " + error.getMessage());
                        }
                    });
                }
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference();

        lnch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (dnr.isChecked()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Lunch");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {

                                int ln = snapshot.getValue(Integer.class); // Assumes the value in the database is an integer
                                ln += 1;
                                databaseReference.setValue(ln);

                            } catch (
                                    Exception e) {
                                Log.e("Error", "Failed to update value in the database: " + e.getMessage());
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Error", "Database operation canceled: " + error.getMessage());
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAuth.signOut();
        Intent i = new Intent(Studentpage.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}