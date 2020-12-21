package com.sorimachi.fastfoodapp.data.model;

public class Customer {
    private int customerCode;
    private String phone, password, email, name, sex, birthDate, address;
    private int getRegisterDate;

    public Customer(int customerCode, String phone, String password, String email, String name, String sex, String birthDate, String address, int getRegisterDate) {
        this.customerCode = customerCode;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.getRegisterDate = getRegisterDate;
    }

    public int getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(int customerCode) {
        this.customerCode = customerCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGetRegisterDate() {
        return getRegisterDate;
    }

    public void setGetRegisterDate(int getRegisterDate) {
        this.getRegisterDate = getRegisterDate;
    }
}
