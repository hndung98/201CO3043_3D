package com.sorimachi.fastfoodapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.ui.login.LoginActivity;

public class HomeUserActivity extends AppCompatActivity {

    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        btnSignOut = (Button)findViewById(R.id.btn_sign_out);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(HomeUserActivity.this, LoginActivity.class);
                startActivity(signInIntent);
                finish();
            }
        });

    }
}