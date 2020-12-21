package com.sorimachi.fastfoodapp.data.model;

public class User {
    String phone, password;
    int type;

    public User(){}

    public User(String phone, String password, int type) {
        this.phone = phone;
        this.password = password;
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
