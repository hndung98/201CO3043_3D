package com.sorimachi.fastfoodapp.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.ui.login.LoginActivity;

public class ShopMainActivity extends AppCompatActivity {

    private int shopCode;
    String phone;

    CardView cvFood, cvSignOut, cvBill, cvHistory, cvAccount, cvRevenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);

        phone = getIntent().getExtras().getString("phone");

        Mapping();
        ControlButton();
    }

    private void ControlButton() {
        cvBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopMainActivity.this, ShopBillManagerActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });

        cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShopMainActivity.this, "history", Toast.LENGTH_SHORT).show();
            }
        });

        cvFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopMainActivity.this, ShopFoodManagerActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });

        cvRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShopMainActivity.this, "revenue", Toast.LENGTH_SHORT).show();
            }
        });

        cvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShopMainActivity.this, "account", Toast.LENGTH_SHORT).show();
            }
        });

        cvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopMainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Mapping() {
        cvFood = findViewById(R.id.cvFood);
        cvSignOut = findViewById(R.id.cvSignOut);
        cvBill = findViewById(R.id.cvBill);
        cvHistory = findViewById(R.id.cvHistory);
        cvAccount = findViewById(R.id.cvAccount);
        cvRevenue = findViewById(R.id.cvRevenue);
    }
}