package com.sorimachi.fastfoodapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sorimachi.fastfoodapp.R;

public class CustomerProfileActivity extends AppCompatActivity {

    Button btnHome;

    String code, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        phone = getIntent().getExtras().getString("phone");
        code = getIntent().getExtras().getString("code");

        Mapping();
        ControlButton();
    }

    private void ControlButton() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerProfileActivity.this, CustomerMainActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("code", code);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Mapping() {
        btnHome = findViewById(R.id.btn_home);
    }
}