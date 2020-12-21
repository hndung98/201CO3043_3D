package com.sorimachi.fastfoodapp.data.model;

public class ModelFoodInCart {
    private int foodCode;
    private String name, price;
    private int amount;

    public ModelFoodInCart(int foodCode, String name, int amount, String price) {
        this.foodCode = foodCode;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
