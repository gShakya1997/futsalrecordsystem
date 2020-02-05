package com.futsalrecord.futsalinfosystem;

import com.futsalrecord.futsalinfosystem.bll.RegistrationBLL;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void testRegister() {
        RegistrationBLL registrationBLL = new RegistrationBLL();
        boolean result = registrationBLL.registerFutsal("test214233335454", "ktm", "ema@gmail.com", "9860918273", "test<3", "20:00", "07:00", "200", "imageFile-1580922140717.jpg");
        assertEquals(true, result);
    }

    @Test
    public void testRegisterUser() {
        RegistrationBLL registrationBLL = new RegistrationBLL();
        boolean result = registrationBLL.registerUser("test61334", "ktm", "test@gmail.com", "9860918274", "test<3", "male", "imageFile-1580922140717.jpg");
        assertEquals(true, result);
    }
}
