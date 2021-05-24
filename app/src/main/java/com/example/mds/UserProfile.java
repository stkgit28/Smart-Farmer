package com.example.mds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    Button deleteButton;
    Button editButton;
    DBHelper database;
    TextView username;
    TextView email;
    TextView phoneNumber;
    ImageButton backMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        database = new DBHelper(this);
        SessionManagement sessionManagement = new SessionManagement(UserProfile.this);
        String emailUser = sessionManagement.getSession();
        System.out.println("aici"+emailUser);
        if (emailUser == null) {
            Toast.makeText(getApplicationContext(), "No user logged in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UserProfile.this, MainActivity.class);
                startActivity(i);
            }
        else {
            ArrayList<String> str = database.getUserInfo(emailUser);
            email = findViewById(R.id.seeEmail);
            username = findViewById(R.id.seeUsername);
            phoneNumber = findViewById(R.id.seePhoneNumber);
            email.setText(str.get(0));
            username.setText(str.get(2));
            phoneNumber.setText(str.get(3));
            final String email = String.valueOf(str.get(0));


            deleteButton = findViewById(R.id.deleteAccount);
            deleteButton.setOnClickListener(v -> {
                database.deleteUser(email);
                Intent i = new Intent(UserProfile.this, RegisterActivity.class);
                startActivity(i);
            });

            editButton = findViewById(R.id.editAccount);
            editButton.setOnClickListener(v -> {
                Intent i = new Intent(UserProfile.this, UserProfileEdit.class);
                i.putExtra("email",emailUser);
                startActivity(i);
            });

            backMain = findViewById(R.id.backMain);
            backMain.setOnClickListener(v -> {
                Intent i = new Intent(UserProfile.this, MainActivity.class);
                i.putExtra("email",emailUser);
                startActivity(i);
            });

            }
        }


    }

