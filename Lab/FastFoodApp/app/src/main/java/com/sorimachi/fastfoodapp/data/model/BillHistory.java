package com.sorimachi.fastfoodapp.data.model;

public class BillHistory {
    private int billCode, customerCode, shopCode, ymd, totalprice;

    public BillHistory(int billCode, int customerCode, int shopCode, int ymd, int totalprice) {
        this.billCode = billCode;
        this.customerCode = customerCode;
        this.shopCode = shopCode;
        this.ymd = ymd;
        this.totalprice = totalprice;
    }

    public int getBillCode() {
        return billCode;
    }

    public void setBillCode(int billCode) {
        this.billCode = billCode;
    }

    public int getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(int customerCode) {
        this.customerCode = customerCode;
    }

    public int getShopCode() {
        return shopCode;
    }

    public void setShopCode(int shopCode) {
        this.shopCode = shopCode;
    }

    public int getYmd() {
        return ymd;
    }

    public void setYmd(int ymd) {
        this.ymd = ymd;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
