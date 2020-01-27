package com.futsalrecord.futsalinfosystem.bll;

import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.api.UsersAPI;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.serverResponse.RegisterResponse;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<RegisterResponse> usersCall = usersAPI.checkUser(username, password);
        try {
            Response<RegisterResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() && loginResponse.body().getStatus()
                    .equals("Login successful")) {
                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

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
