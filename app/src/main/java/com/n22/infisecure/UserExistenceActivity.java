package com.n22.infisecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserExistenceActivity extends AppCompatActivity {

    Button newUser, oldUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_existence);
        newUser = findViewById(R.id.newUser);
        oldUser = findViewById(R.id.oldUser);
        // when the user clicks on new user button
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserExistenceActivity.this, UserSignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // when the user clicks on existing user button
        oldUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}