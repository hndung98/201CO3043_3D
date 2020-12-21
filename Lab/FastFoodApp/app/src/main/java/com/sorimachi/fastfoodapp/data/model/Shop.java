package com.sorimachi.fastfoodapp.data.model;

import java.util.ArrayList;

public class Shop {
    private String shopCode;
    private String phone, name, address, image;
    private ArrayList<String> keyList;
    private ArrayList<Food> foodList;

    public Shop(){}

    public Shop(String shopCode, String phone, String name, String address, String image, ArrayList<String> keyList, ArrayList<Food> foodList) {
        this.shopCode = shopCode;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.image = image;
        this.keyList = keyList;
        this.foodList = foodList;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public ArrayList<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(ArrayList<String> keyList) {
        this.keyList = keyList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
