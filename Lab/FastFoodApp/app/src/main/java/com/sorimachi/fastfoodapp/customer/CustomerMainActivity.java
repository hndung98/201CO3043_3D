package com.sorimachi.fastfoodapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.data.model.ModelShop;
import com.sorimachi.fastfoodapp.adapter.ShopAdapter;
import com.sorimachi.fastfoodapp.data.model.Shop;
import com.sorimachi.fastfoodapp.ui.login.LoginActivity;

import java.util.ArrayList;

public class CustomerMainActivity extends AppCompatActivity {

    RecyclerView rvFavouriteShop, rvRecommendShop;
    ArrayList<ModelShop> lstFavouriteShops, lstRecommendShop;

    ImageButton btnHome, btnHistory, btnProfile, btnSignOut;
    SearchView searchView;

    ShopAdapter adapterFavourite, adapterRecommend;
    LinearLayoutManager layoutFavouriteManager, layoutRecommendManager;
    RecyclerView.LayoutManager rvFavouriteLiLayoutManager, rvRecommendLiLayoutManager;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;

    private String code, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        code = getIntent().getExtras().getString("code");
        phone = getIntent().getExtras().getString("phone");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Mapping();
        ControlButton();

        lstRecommendShop = new ArrayList<>();
        lstFavouriteShops = new ArrayList<>();


        LoadFavouriteShops();
        LoadRecommendShops();
    }




    private void LoadFavouriteShops(){
        Toast.makeText(CustomerMainActivity.this,"Please wait a few seconds to load data", Toast.LENGTH_SHORT).show();
        database.getReference("shop").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Shop shop = postSnapshot.getValue(Shop.class);
                    storageReference.child(shop.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            lstFavouriteShops.add(new ModelShop(shop.getShopCode(), shop.getName(), uri.toString(), shop.getAddress()));

                            layoutFavouriteManager = new LinearLayoutManager(CustomerMainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            rvFavouriteLiLayoutManager = layoutFavouriteManager;
                            rvFavouriteShop.setLayoutManager(rvFavouriteLiLayoutManager);
                            adapterFavourite = new ShopAdapter(CustomerMainActivity.this, lstFavouriteShops);

                            rvFavouriteShop.setAdapter(adapterFavourite);
                            adapterFavourite.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent intent = new Intent(CustomerMainActivity.this, CustomerFoodListActivity.class);
                                    intent.putExtra("shop-code", lstFavouriteShops.get(position).getCode());
                                    intent.putExtra("code", code);
                                    intent.putExtra("phone", phone);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void LoadRecommendShops(){
        database.getReference("shop").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Shop shop = postSnapshot.getValue(Shop.class);
                    storageReference.child(shop.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            lstRecommendShop.add(new ModelShop(shop.getShopCode(), shop.getName(), uri.toString(), shop.getAddress()));

                            layoutRecommendManager = new LinearLayoutManager(CustomerMainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            rvRecommendLiLayoutManager = layoutRecommendManager;
                            rvRecommendShop.setLayoutManager(rvRecommendLiLayoutManager);

                            adapterRecommend = new ShopAdapter(CustomerMainActivity.this, lstRecommendShop);

                            rvRecommendShop.setAdapter(adapterRecommend);

                            adapterRecommend.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent intent = new Intent(CustomerMainActivity.this, CustomerFoodListActivity.class);
                                    intent.putExtra("shop-code", lstFavouriteShops.get(position).getCode());
                                    intent.putExtra("code", code);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void Mapping(){
        rvFavouriteShop = findViewById(R.id.rv_favourite_shop);
        rvRecommendShop = findViewById(R.id.rv_recommend_shop);
        btnHome = findViewById(R.id.btn_home);
        btnProfile = findViewById(R.id.btn_profile);
        btnHistory = findViewById(R.id.btn_history);
        btnSignOut = findViewById(R.id.btn_sign_out);
//        searchView = findViewById(R.id.search_view);
    }

    private void ControlButton(){
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerProfileActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("code", code);
                startActivity(intent);
                finish();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerMainActivity.this, "home", Toast.LENGTH_SHORT).show();
            }
        });
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void StartAdapter(){
        layoutFavouriteManager = new LinearLayoutManager(CustomerMainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvFavouriteLiLayoutManager = layoutFavouriteManager;
        rvFavouriteShop.setLayoutManager(rvFavouriteLiLayoutManager);
        adapterFavourite = new ShopAdapter(CustomerMainActivity.this, lstFavouriteShops);

        rvFavouriteShop.setAdapter(adapterFavourite);
        adapterFavourite.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerFoodListActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("position", position);
                intent.putExtra("shop-code", 1);
                startActivity(intent);
                finish();
            }
        });

        layoutRecommendManager = new LinearLayoutManager(CustomerMainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvRecommendLiLayoutManager = layoutRecommendManager;
        rvRecommendShop.setLayoutManager(rvRecommendLiLayoutManager);

        adapterRecommend = new ShopAdapter(CustomerMainActivity.this, lstRecommendShop);

        rvRecommendShop.setAdapter(adapterRecommend);

        adapterRecommend.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerFoodListActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadImage(String uri, ImageView image){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.capsule)
                .error(R.drawable.capsule)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(this)
                .load(uri)
                .apply(options)
                .into(image);
    }
}