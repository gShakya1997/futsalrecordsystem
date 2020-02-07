package com.futsalrecord.futsalinfosystem;

import com.futsalrecord.futsalinfosystem.bll.LoginBLL;
import com.futsalrecord.futsalinfosystem.bll.RegistrationBLL;
import com.futsalrecord.futsalinfosystem.bll.Validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class LoginRegisterTest {
    private String futsalName;
    private String futsalAddress;
    private String futsalEmail;
    private String futsalPhone;
    private String futsalPassword;
    private String futsalCpassword;
    private String futsalOpeningTime;
    private String futsalClosingTime;
    private String futsalPrice;
    private String futsalImage;
    private String username;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String image;
    private RegistrationBLL registrationBLL = null;
    private LoginBLL loginBLL = null;
    private Validation validation = null;

    @Before
    public void setUpObject() {
        registrationBLL = new RegistrationBLL();
        loginBLL = new LoginBLL();
        validation = new Validation();
        futsalName = "UnitTest2";
        futsalAddress = "KTM";
        futsalEmail = "y@gmail.cos";
        futsalPhone = "9860918273";
        futsalPassword = "test<3";
        futsalCpassword = "test<3";
        futsalOpeningTime = "07:00 AM";
        futsalClosingTime = "10:00 PM";
        futsalPrice = "150";
        futsalImage = "imageFile-1581014015039.jpeg";
        username = "UnitTest2";
        address = "Patan";
        email = "test@gmail.com";
        phone = "9818086354";
        password = "test<3";
        gender = "Female";
        image = "imageFile-1581014015039.jpeg";
    }

    @Test
    public void testValidation() {
        String[] expectResultForString = {"noError", "noError", "noError", "noError"};
        String[] resultString = {
                validation.validatePrice(futsalPrice),
                validation.validatePhone(futsalPhone),
                validation.validatePassword(futsalPassword),
                validation.validateConfirmPassword(futsalPassword, futsalCpassword),
        };

        boolean[] expectResultForBoolean = {true, true, true, true};
        boolean[] resultBoolean = {
                validation.validateFutsalName(futsalName),
                validation.validateAddress(futsalAddress),
                validation.validateOpeningTime(futsalOpeningTime),
                validation.validateClosingTime(futsalClosingTime)
        };
        assertArrayEquals(expectResultForBoolean, resultBoolean);
        assertArrayEquals(expectResultForString, resultString);
    }

    @Test
    public void testRegister() {
        boolean result = registrationBLL.registerFutsal(futsalName, futsalAddress, futsalEmail, futsalPhone,
                futsalPassword, futsalOpeningTime, futsalClosingTime, futsalPrice, futsalImage);
        assertEquals(true, result);
    }

    @Test
    public void testRegisterUser() {
        boolean result = registrationBLL.registerUser(username, address, email, phone, password, gender, image);
        assertEquals(true, result);
    }

    @Test
    public void testLogin() {
        boolean result = loginBLL.checkFutsal("test123", "test<3");
        assertEquals(true, result);
    }

    @Test
    public void testLoginUser() {
        boolean result = loginBLL.checkUser("test123", "test<3");
        assertEquals(true, result);
    }

    @After
    public void shutDown() {
        registrationBLL = null;
        loginBLL = null;
        validation = null;
        futsalName = null;
        futsalAddress = null;
        futsalEmail = null;
        futsalPhone = null;
        futsalPassword = null;
        futsalCpassword = null;
        futsalOpeningTime = null;
        futsalClosingTime = null;
        futsalPrice = null;
        futsalImage = null;
        username = null;
        address = null;
        email = null;
        phone = null;
        password = null;
        gender = null;
        image = null;
    }
}
