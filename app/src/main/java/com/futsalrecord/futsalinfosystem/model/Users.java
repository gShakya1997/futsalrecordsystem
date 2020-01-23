package com.futsalrecord.futsalinfosystem.model;

public class Users {
    private String username;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String image;

    public Users(String username, String address, String email, String phone, String password, String gender, String image) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
