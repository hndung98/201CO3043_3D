package com.sorimachi.fastfoodapp.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sorimachi.fastfoodapp.R;

public class ShopProfileActivity extends AppCompatActivity {

    String phone;

    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);

        phone = getIntent().getExtras().getString("phone");

        Mapping();
        ControlButton();
    }

    private void ControlButton() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopProfileActivity.this, ShopMainActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Mapping() {
        btnHome = findViewById(R.id.btn_home);
    }
}