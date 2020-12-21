package com.sorimachi.fastfoodapp.data.model;

public class Order {
    private int price, amount, discount;
    private String foodName;
    public Order(){}

    public Order(String foodName, int price, int amount, int discount) {
        this.foodName = foodName;
        this.price = price;
        this.amount = amount;
        this.discount = discount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
