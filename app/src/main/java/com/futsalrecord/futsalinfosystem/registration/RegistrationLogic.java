package com.futsalrecord.futsalinfosystem.registration;

import java.util.regex.Pattern;

public class RegistrationLogic {
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=<>])" +    //at least 1 special character
                    ".{6,}" +               //at least 6 characters
                    "$");
}


