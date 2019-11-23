package com.futsalrecord.futsalinfosystem.model;

public class Customers {
    private String customerFullName, customerEmail, customerPhoneNo, customerGender, customerAddress;
    private int customerImageId;

    public Customers(String customerFullName, String customerEmail, String customerPhoneNo, String customerGender, String customerAddress, int customerImageId) {
        this.customerFullName = customerFullName;
        this.customerEmail = customerEmail;
        this.customerPhoneNo = customerPhoneNo;
        this.customerGender = customerGender;
        this.customerAddress = customerAddress;
        this.customerImageId = customerImageId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerImageId() {
        return customerImageId;
    }

    public void setCustomerImageId(int customerImageId) {
        this.customerImageId = customerImageId;
    }
}
