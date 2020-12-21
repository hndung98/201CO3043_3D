package com.sorimachi.fastfoodapp.data.model;

public class ModelFood {
    private int foodCode;
    private String image, name, status, price;

    public ModelFood(int foodCode, String image, String name, String status, String price) {
        this.foodCode = foodCode;
        this.image = image;
        this.name = name;
        this.status = status;
        this.price = price;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(int foodCode) {
        this.foodCode = foodCode;
    }
}
