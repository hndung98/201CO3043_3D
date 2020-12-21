package com.sorimachi.fastfoodapp.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.adapter.BillAdapter;
import com.sorimachi.fastfoodapp.adapter.FoodInCartAdapter;
import com.sorimachi.fastfoodapp.customer.CustomerFoodListActivity;
import com.sorimachi.fastfoodapp.data.model.Bill;
import com.sorimachi.fastfoodapp.data.model.ModelFoodInCart;
import com.sorimachi.fastfoodapp.data.model.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShopBillManagerActivity extends AppCompatActivity {

    RecyclerView recyclerView, rvListCart;
    ArrayList<Bill> lstBill;
    ArrayList<Order> lstOrder;
    String phone;
    ArrayList<ModelFoodInCart> lstModelFoosInCart;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_bill_manager);

        database = FirebaseDatabase.getInstance();

        phone = getIntent().getExtras().getString("phone");

        lstModelFoosInCart = new ArrayList<>();

        Mapping();

        ControlButton();

        Date curDate = new Date();
        String strCurDate = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
        String strCurTime = new SimpleDateFormat("HH:mm:ss").format(curDate);
        strCurDate = strCurDate.replace("-","");
        strCurTime = strCurTime.replace(":","");
        String finalStrCurDate = strCurDate;
        database.getReference("bill").child(strCurDate).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lstBill = new ArrayList<>();
                lstOrder = new ArrayList<>();

                recyclerView = findViewById(R.id.rvList);

                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    Bill bill = postSnapshot.getValue(Bill.class);
                    if(bill.getShopCode().equals(phone) && bill.getStatus() == 0){
                        lstBill.add(bill);
                    }
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(ShopBillManagerActivity.this);
                RecyclerView.LayoutManager layoutManager1 =  layoutManager;
                recyclerView.setLayoutManager(layoutManager1);

                BillAdapter adapter = new BillAdapter(ShopBillManagerActivity.this, lstBill);

                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new BillAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        lstModelFoosInCart.clear();
                        lstOrder = lstBill.get(position).getOrdersList();
                        for(Order order: lstOrder){
                            lstModelFoosInCart.add(new ModelFoodInCart(0, order.getFoodName(), order.getAmount(), order.getPrice()+""));
                        }
                        final Dialog dialog = new Dialog(ShopBillManagerActivity.this);
                        dialog.setCancelable(true);
                        dialog.setContentView(R.layout.layout_customer_current_cart);
                        dialog.show();

                        rvListCart = dialog.findViewById(R.id.rvList);
                        LinearLayoutManager layoutManagerCart = new LinearLayoutManager(dialog.getContext());
                        RecyclerView.LayoutManager rvLayoutManagerCart = layoutManagerCart;
                        rvListCart.setLayoutManager(rvLayoutManagerCart);

                        FoodInCartAdapter adapterCart = new FoodInCartAdapter(dialog.getContext(), lstModelFoosInCart, false);
                        rvListCart.setAdapter(adapterCart);

                        Button btn_ok = dialog.findViewById(R.id.btn_ok);
                        btn_ok.setText("Xác nhận");
                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                lstBill.get(position).setStatus(1);
                                database.getReference("bill").child(finalStrCurDate).child(lstBill.get(position).getBillCode()).setValue(lstBill.get(position));
                            }
                        });
                    }
                });

                cont();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void cont(){
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ShopBillManagerActivity.this, "hello",Toast.LENGTH_SHORT).show();
            }
        }, 4000);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShopBillManagerActivity.this, ShopMainActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
    }


    private void ControlButton() {
    }

    private void Mapping() {
    }
}