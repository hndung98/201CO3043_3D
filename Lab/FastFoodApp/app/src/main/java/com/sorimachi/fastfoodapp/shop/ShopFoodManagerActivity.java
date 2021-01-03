package com.sorimachi.fastfoodapp.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.adapter.FoodAdapter;
import com.sorimachi.fastfoodapp.data.model.ModelFood;
import com.sorimachi.fastfoodapp.ui.login.LoginActivity;

import java.util.ArrayList;

public class ShopFoodManagerActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference, databaseReferenceUser;



    RecyclerView rvList;
    Button btnAdd, btnHome,btnSignOut;

    ArrayList<ModelFood> lstModelFoods;

    int shopCode;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_food_manager);

        phone = getIntent().getExtras().getString("phone");

        Mapping();
        ControlButton();

        lstModelFoods = new ArrayList<>();
        lstModelFoods.add(new ModelFood(1, "", "Food 1", "new", "10000"));
        lstModelFoods.add(new ModelFood(2, "", "Food 2", "new", "10000"));
        lstModelFoods.add(new ModelFood(3, "", "Food 3", "new", "10000"));
        lstModelFoods.add(new ModelFood(4, "", "Food 4", "new", "10000"));

        LoadFoodsList();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShopFoodManagerActivity.this, ShopMainActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
    }

    private void LoadFoodsList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(ShopFoodManagerActivity.this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        rvList.setLayoutManager(rvLayoutManager);

        FoodAdapter adapter = new FoodAdapter(ShopFoodManagerActivity.this, lstModelFoods, "Xoá");

        rvList.setAdapter(adapter);

        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                lstModelFoods.remove(position);
                LoadFoodsList();
                Toast.makeText(ShopFoodManagerActivity.this, "pos: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeItemFood(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(ShopFoodManagerActivity.this);
        RecyclerView.LayoutManager rvLayoutManager = layoutManager;
        rvList.setLayoutManager(rvLayoutManager);

        FoodAdapter adapter = new FoodAdapter(ShopFoodManagerActivity.this, lstModelFoods, "Xoá");

        rvList.setAdapter(adapter);

        adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                lstModelFoods.remove(position);
                LoadFoodsList();
                Toast.makeText(ShopFoodManagerActivity.this, "pos: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ControlButton() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopFoodManagerActivity.this, ShopAddFoodActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopFoodManagerActivity.this, ShopMainActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopFoodManagerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Mapping() {
        rvList = findViewById(R.id.rvList);
        btnAdd = findViewById(R.id.btn_add);
        btnHome = findViewById(R.id.btn_home);
        btnSignOut = findViewById(R.id.btn_sign_out);
    }
}