package com.futsalrecord.futsalinfosystem;

import com.futsalrecord.futsalinfosystem.bll.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginTest {

    @Test
    public void testLogin(){
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkFutsal("test123","test<3");
        assertEquals(true,result);
    }
}
