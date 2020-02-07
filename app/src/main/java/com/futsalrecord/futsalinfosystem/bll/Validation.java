package com.futsalrecord.futsalinfosystem.bll;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validation {

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=<>_!*().,])" +    //at least 1 special character
                    ".{6,}" +               //at least 6 characters
                    "$");

    private String throwError;

    public boolean validateFutsalName(String regName) {
        return !regName.isEmpty();
    }

    public boolean validateAddress(String regAddress) {
        return !regAddress.isEmpty();
    }

    public boolean validateOpeningTime(String regOpeningTime) {
        return !regOpeningTime.isEmpty();
    }

    public boolean validateClosingTime(String regClosingTime) {
        return !regClosingTime.isEmpty();
    }

    public String validatePrice(String regPrice) {
        if (regPrice.isEmpty()) {
            throwError = "required";
            return throwError;
        } else if (regPrice.length() > 4) {
            throwError = "overPriced";
            return throwError;
        } else {
            throwError = "noError";
            return throwError;
        }
    }

    public String validateEmail(String regEmail) {
        if (regEmail.isEmpty()) {
            throwError = "required";
            return throwError;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(regEmail).matches()) {
            throwError = "invalidEmail";
            return throwError;
        } else {
            throwError = "noError";
            return throwError;
        }
    }

    public String validatePhone(String regPhone) {
        if (regPhone.isEmpty()) {
            throwError = "required";
            return throwError;
        } else if (regPhone.length() > 11) {
            throwError = "invalidPhone";
            return throwError;
        } else {
            throwError = "noError";
            return throwError;
        }
    }

    public String validatePassword(String regPassword) {
        if (regPassword.isEmpty()) {
            throwError = "required";
            return throwError;
        } else if (!PASSWORD_PATTERN.matcher(regPassword).matches()) {
            throwError = "weakPassword";
            return throwError;
        } else {
            throwError = "noError";
            return throwError;
        }
    }

    public String validateConfirmPassword(String regPassword, String regCpassword) {
        if (!regPassword.equals(regCpassword)) {
            throwError = "!Password";
            return throwError;
        } else if (regCpassword.isEmpty()) {
            throwError = "required";
            return throwError;
        } else {
            throwError = "noError";
            return throwError;
        }
    }

    public String validateUsername(String regName) {
        if (regName.isEmpty()) {
            throwError = "required";
            return throwError;
        } else if (regName.length() > 31) {
            throwError = "usernameTooLong";
            return throwError;
        } else if (regName.length() < 7) {
            throwError = "usernameTooShort";
            return throwError;
        } else {
            throwError = "noError";
            return throwError;
        }
    }
}
