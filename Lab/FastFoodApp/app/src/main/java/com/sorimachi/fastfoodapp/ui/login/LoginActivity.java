package com.sorimachi.fastfoodapp.ui.login;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.sorimachi.fastfoodapp.data.model.User;
import com.sorimachi.fastfoodapp.shop.ShopMainActivity;
import com.sorimachi.fastfoodapp.customer.CustomerFoodListActivity;
import com.sorimachi.fastfoodapp.customer.CustomerMainActivity;

public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference, databaseReferenceUser;

    FirebaseStorage storage;
    StorageReference storageReference;

    EditText edtUsername, edtPassword;
    Button btnSignIn;
    TextView btnSignUp;
    ImageView imageLogo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("message");
        databaseReferenceUser = database.getReference("user");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("images/2-2-junk-food-png-image-thumb.png");

        Mapping();

        ControlButton(); 

        edtUsername.setText("s");
        edtPassword.setText("a");

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.capsule)
                .error(R.drawable.capsule)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/fastfood-3b635.appspot.com/o/images%2Fshop4.jpg?alt=media&token=f8c7140e-41a3-4ef8-905d-14e01fb950cd")
                .apply(options)
                .into(imageLogo);
    }

    private void SignIn(String username, String password)
    {
        databaseReferenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(username).exists()){
                    User user = snapshot.child(username).getValue(User.class);
                    if(user.getPassword().equals(password)){
                        Intent intent;
                        if(user.getType() == 0){
                            intent = new Intent(LoginActivity.this, ShopMainActivity.class);
                        }
                        else {
                            intent = new Intent(LoginActivity.this, CustomerMainActivity.class);
                        }
                        intent.putExtra("code", edtUsername.getText().toString());
                        intent.putExtra("phone", user.getPhone());
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "username or password incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void SignUp()
    {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("hello").exists())
                {
                    String str = snapshot.child("hello").getValue().toString();
                    Toast.makeText(LoginActivity.this, "sign up: "+ str, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, CustomerFoodListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, "sign up failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ControlButton()
    {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsername.length() == 0 || edtPassword.length() == 0){
                    Toast.makeText(LoginActivity.this, "type username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                SignIn(edtUsername.getText().toString(), edtPassword.getText().toString());
//                imageView = findViewById(R.id.image_logo);
//                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                edtUsername.setText(bitmap.toString());
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageReference ref = storage.getReference().child("images/shop4.jpg");
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        edtUsername.setText(uri.toString());
                    }
                });
//                try {
//                    final File file = File.createTempFile("image", "jpg|png");
//                    storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                            imageLogo.setImageBitmap(bitmap);
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Intent intent = new Intent(LoginActivity.this, ShopAddFoodActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

    }

    private void Mapping()
    {
        edtUsername = (EditText)findViewById(R.id.edt_username);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        btnSignIn = (Button)findViewById(R.id.btn_sign_in);
        btnSignUp = (TextView)findViewById(R.id.btn_sign_up);
        imageLogo = findViewById(R.id.image_logo);
    }

}