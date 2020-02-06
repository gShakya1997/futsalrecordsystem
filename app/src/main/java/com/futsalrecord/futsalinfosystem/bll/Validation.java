package com.futsalrecord.futsalinfosystem.bll;

import android.util.Patterns;

import com.futsalrecord.futsalinfosystem.activities.registration.RegistrationLogic;

public class Validation{
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
        } else if (!RegistrationLogic.PASSWORD_PATTERN.matcher(regPassword).matches()) {
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
}
