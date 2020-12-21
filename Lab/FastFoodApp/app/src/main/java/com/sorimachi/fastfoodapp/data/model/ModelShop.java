package com.sorimachi.fastfoodapp.data.model;

import android.widget.ImageView;

public class ModelShop {
    private String code;
    private String name, image, address;

    public ModelShop(String code, String name, String image, String address) {
        this.code = code;
        this.image = image;
        this.name = name;
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
