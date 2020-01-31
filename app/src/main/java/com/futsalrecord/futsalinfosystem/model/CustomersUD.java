package com.futsalrecord.futsalinfosystem.model;

public class CustomersUD {
    private String _id, customerFullname, customerEmail, customerPhoneNo, customerGender,
            customerAddress;

    public CustomersUD(String _id, String customerFullname, String customerEmail, String customerPhoneNo, String customerGender, String customerAddress) {
        this._id = _id;
        this.customerFullname = customerFullname;
        this.customerEmail = customerEmail;
        this.customerPhoneNo = customerPhoneNo;
        this.customerGender = customerGender;
        this.customerAddress = customerAddress;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
