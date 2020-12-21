package com.sorimachi.fastfoodapp.shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.data.model.Food;
import com.sorimachi.fastfoodapp.data.model.Shop;

import java.util.Calendar;
import java.util.Date;

public class ShopAddFoodActivity extends AppCompatActivity {

    public Uri imageUri;
    ImageView imageView;
    Button btn_confirm, btn_back;
    EditText edtName, edtPrice, edtUnit;

    FirebaseDatabase database;
    DatabaseReference databaseReferenceShop;
    FirebaseStorage storage;
    StorageReference storageReference;

    int shopCode;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_add_food);
        phone = getIntent().getExtras().getString("phone");

        imageView = findViewById(R.id.image_food);
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_back = findViewById(R.id.btn_back);
        edtName = findViewById(R.id.edt_name);
        edtPrice = findViewById(R.id.edt_price);
        edtUnit = findViewById(R.id.edt_unit);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        database = FirebaseDatabase.getInstance();
        databaseReferenceShop = database.getReference("shop");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().length() == 0 || edtPrice.getText().length() == 0 || edtUnit.getText().length() == 0){
                    Toast.makeText(ShopAddFoodActivity.this, "please type all fields.",Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseReferenceShop.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(phone).exists()){
                            Shop shop = snapshot.child(phone).getValue(Shop.class);

                            Food food = new Food();

                            int newFoodCode = shop.getFoodList().size();
                            food.setFoodCode(newFoodCode);

                            food.setName(edtName.getText().toString());
                            food.setPrice(Integer.parseInt(edtPrice.getText().toString()));
                            food.setUnit(edtUnit.getText().toString());

                            Date currentTime = Calendar.getInstance().getTime();
                            String imageName = "" + phone + currentTime.getTime();
                            String imagePath = "images/food/" + imageName;
                            food.setImage(imagePath);

                            UploadPicture(imageName);
                            databaseReferenceShop.child(phone).child("foodList").child(""+newFoodCode).setValue(food);

                            edtName.setText("");
                            edtPrice.setText("");
                            edtUnit.setText("");
                            Toast.makeText(ShopAddFoodActivity.this, "added new food", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                String path = shopCode+"/foodList";
//                Date currentTime = Calendar.getInstance().getTime();
//                String imageName = "" + phone + currentTime.getTime();
//                Toast.makeText(ShopAddFoodActivity.this, imageName,Toast.LENGTH_SHORT).show();
//                UploadPicture(imageName);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopAddFoodActivity.this, ShopFoodManagerActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShopAddFoodActivity.this, ShopFoodManagerActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
    }

    private void choosePicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
    private void UploadPicture(String name){
        if (imageUri == null){
            Toast.makeText(getApplicationContext(), "no picture to upload", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading...");
        pd.show();

        StorageReference storageReference = this.storageReference.child("images/food/" + name);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Uploaded.", BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double percent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Percentage: " + (int) percent + "%");
                    }
                });
    }
}