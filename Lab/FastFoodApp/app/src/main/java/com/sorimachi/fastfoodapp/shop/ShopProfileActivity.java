package com.sorimachi.fastfoodapp.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.adapter.FoodAdapter;
import com.sorimachi.fastfoodapp.customer.CustomerFoodListActivity;
import com.sorimachi.fastfoodapp.data.model.Food;
import com.sorimachi.fastfoodapp.data.model.ModelFood;
import com.sorimachi.fastfoodapp.data.model.ModelFoodInCart;
import com.sorimachi.fastfoodapp.data.model.Shop;

public class ShopProfileActivity extends AppCompatActivity {
    FirebaseDatabase database;
    String phone;

    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);

        phone = getIntent().getExtras().getString("phone");
        TextView setName = (TextView)findViewById(R.id.name);
        TextView setPhone = (TextView)findViewById(R.id.phone);
        TextView setAddress = (TextView)findViewById(R.id.address);
        TextView setheaderName = (TextView)findViewById(R.id.headerName);
        TextView setheaderPhone = (TextView)findViewById(R.id.headerPhone);
//        myAwesomeTextView.setText(phone);


        database = FirebaseDatabase.getInstance();
        database.getReference("shop").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    Shop shop = postSnapshot.getValue(Shop.class);

                    if(shop.getShopCode().equals(phone)){
                        setheaderName.setText(shop.getName());
                        setheaderPhone.setText(phone);
                        setName.setText(shop.getName());
                        setPhone.setText(phone);
                        setAddress.setText(shop.getAddress());
//                        lstFoods = shop.getFoodList();
//                        for(Food food: lstFoods){
//                            storageReference.child(food.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    lstModelFoods.add(new ModelFood(food.getFoodCode() , uri.toString(), food.getName(), food.getUnit(),food.getPrice()+""));
//                                    LinearLayoutManager layoutManager = new LinearLayoutManager(CustomerFoodListActivity.this);
//                                    RecyclerView.LayoutManager rvLayoutManager = layoutManager;
//                                    rvList.setLayoutManager(rvLayoutManager);
//
//                                    FoodAdapter adapter = new FoodAdapter(CustomerFoodListActivity.this, lstModelFoods, "Thêm");
//
//                                    rvList.setAdapter(adapter);
//
//                                    adapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
//                                        @Override
//                                        public void onItemClick(int position) {
//                                            for(int i = 0; i < lstModelFoosInCart.size(); i++){
//                                                if(lstModelFoosInCart.get(i).getFoodCode() == lstModelFoods.get(position).getFoodCode()){
//                                                    lstModelFoosInCart.get(i).setAmount(lstModelFoosInCart.get(i).getAmount() + 1);
//                                                    return;
//                                                }
//                                            }
//                                            lstModelFoosInCart.add(new ModelFoodInCart(lstModelFoods.get(position).getFoodCode(), lstModelFoods.get(position).getName(), 1, lstModelFoods.get(position).getPrice()));
//                                        }
//                                    });
//                                }
//                            });
//                        }
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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