package com.sorimachi.fastfoodapp.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sorimachi.fastfoodapp.R;

public class ShopFoodBillActivity extends AppCompatActivity {

    Button btnHome;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_food_bill);

        phone = getIntent().getExtras().getString("phone");

        Mapping();
        ControlButton();
    }

    private void Mapping() {
        btnHome = findViewById(R.id.btn_home);
    }

    private void ControlButton() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopFoodBillActivity.this, ShopMainActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });
    }
}