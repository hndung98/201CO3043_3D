package com.sorimachi.fastfoodapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.data.model.Shop;

public class CustomerProfileActivity extends AppCompatActivity {
    FirebaseDatabase database;
    Button btnHome;

    String code, phone;
//    TextView setName = (TextView)findViewById(R.id.name);
//    TextView setPhone = (TextView)findViewById(R.id.phone);
//    TextView setAddress = (TextView)findViewById(R.id.address);
//    TextView setheaderName = (TextView)findViewById(R.id.headerName);
//    TextView setheaderPhone = (TextView)findViewById(R.id.headerPhone);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        phone = getIntent().getExtras().getString("phone");
        code = getIntent().getExtras().getString("code");
//        database = FirebaseDatabase.getInstance();
//        database.getReference("shop").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot postSnapshot: snapshot.getChildren()){
//                    Shop shop = postSnapshot.getValue(Shop.class);
//
//                    if(shop.getShopCode().equals(phone)){
//                        setheaderName.setText(shop.getName());
//                        setheaderPhone.setText(phone);
//                        setName.setText(shop.getName());
//                        setPhone.setText(phone);
//                        setAddress.setText(shop.getAddress());
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
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