package com.futsalrecord.futsalinfosystem.model;

public class Futsal {
    private String futsalName;
    private String futsalAddress;
    private String futsalEmail;
    private String futsalPhone;
    private String futsalPassword;
    private String futsalOpeningTime;
    private String futsalClosingTime;
    private String futsalPrice;
    private String futsalImage;


    public Futsal(String futsalName, String futsalAddress, String futsalEmail, String futsalPhone,
                  String futsalPassword, String futsalOpeningTime, String futsalClosingTime,
                  String futsalPrice, String futsalImage) {
        this.futsalName = futsalName;
        this.futsalAddress = futsalAddress;
        this.futsalEmail = futsalEmail;
        this.futsalPhone = futsalPhone;
        this.futsalPassword = futsalPassword;
        this.futsalOpeningTime = futsalOpeningTime;
        this.futsalClosingTime = futsalClosingTime;
        this.futsalPrice = futsalPrice;
        this.futsalImage = futsalImage;
    }

    public String getFutsalName() {
        return futsalName;
    }

    public void setFutsalName(String futsalName) {
        this.futsalName = futsalName;
    }

    public String getFutsalAddress() {
        return futsalAddress;
    }

    public void setFutsalAddress(String futsalAddress) {
        this.futsalAddress = futsalAddress;
    }

    public String getFutsalEmail() {
        return futsalEmail;
    }

    public void setFutsalEmail(String futsalEmail) {
        this.futsalEmail = futsalEmail;
    }

    public String getFutsalPhone() {
        return futsalPhone;
    }

    public void setFutsalPhone(String futsalPhone) {
        this.futsalPhone = futsalPhone;
    }

    public String getFutsalPassword() {
        return futsalPassword;
    }

    public void setFutsalPassword(String futsalPassword) {
        this.futsalPassword = futsalPassword;
    }

    public String getFutsalOpeningTime() {
        return futsalOpeningTime;
    }

    public void setFutsalOpeningTime(String futsalOpeningTime) {
        this.futsalOpeningTime = futsalOpeningTime;
    }

    public String getFutsalClosingTime() {
        return futsalClosingTime;
    }

    public void setFutsalClosingTime(String futsalClosingTime) {
        this.futsalClosingTime = futsalClosingTime;
    }

    public String getFutsalPrice() {
        return futsalPrice;
    }

    public void setFutsalPrice(String futsalPrice) {
        this.futsalPrice = futsalPrice;
    }

    public String getFutsalImage() {
        return futsalImage;
    }

    public void setFutsalImage(String futsalImage) {
        this.futsalImage = futsalImage;
    }
}