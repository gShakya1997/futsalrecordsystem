package com.study.futsalwear;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkFutsal(String futsalName, String futsalPassword) {
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<RegisterResponse> futsalCall = futsalAPI.checkUser(futsalName, futsalPassword);
        try {
            Response<RegisterResponse> loginResponse = futsalCall.execute();
            if (loginResponse.isSuccessful() && loginResponse.body().getStatus()
                    .equals("Login successful")) {
                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
