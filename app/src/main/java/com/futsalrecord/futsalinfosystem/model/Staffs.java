package com.futsalrecord.futsalinfosystem.model;

public class Staffs {
    private String staffFullName, staffEmail, staffPhoneNo, staffGender;
    private int staffImageId;

    public Staffs(String staffFullName, String staffEmail, String staffPhoneNo, String staffGender, int staffImageId) {
        this.staffFullName = staffFullName;
        this.staffEmail = staffEmail;
        this.staffPhoneNo = staffPhoneNo;
        this.staffGender = staffGender;
        this.staffImageId = staffImageId;
    }

    public String getStaffFullName() {
        return staffFullName;
    }

    public void setStaffFullName(String staffFullName) {
        this.staffFullName = staffFullName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPhoneNo() {
        return staffPhoneNo;
    }

    public void setStaffPhoneNo(String staffPhoneNo) {
        this.staffPhoneNo = staffPhoneNo;
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public int getStaffImageId() {
        return staffImageId;
    }

    public void setStaffImageId(int staffImageId) {
        this.staffImageId = staffImageId;
    }
}
