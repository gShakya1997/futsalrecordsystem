package com.futsalrecord.futsalinfosystem.model;

public class Customers {
    private String customerFullname, customerEmail, customerPhoneNo, customerGender,
            customerAddress;

    public Customers(String customerFullname, String customerEmail, String customerPhoneNo, String customerGender, String customerAddress) {
        this.customerFullname = customerFullname;
        this.customerEmail = customerEmail;
        this.customerPhoneNo = customerPhoneNo;
        this.customerGender = customerGender;
        this.customerAddress = customerAddress;
    }

    public String getCustomerFullname() {
        return customerFullname;
    }

    public void setCustomerFullname(String customerFullname) {
        this.customerFullname = customerFullname;
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
}
