package com.futsalrecord.futsalinfosystem.bll;

import com.futsalrecord.futsalinfosystem.api.FutsalAPI;
import com.futsalrecord.futsalinfosystem.api.UsersAPI;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.model.Users;
import com.futsalrecord.futsalinfosystem.serverResponse.RegisterResponse;
import com.futsalrecord.futsalinfosystem.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegistrationBLL {
    private boolean isSuccess = false;

    public boolean registerFutsal(String futsalName, String futsalAddress, String futsalEmail, String futsalPhone, String futsalPassword
            , String futsalOpeningTime, String futsalClosingTime, String futsalPrice, String futsalImage) {

        Futsal futsal = new Futsal(futsalName, futsalAddress, futsalEmail, futsalPhone, futsalPassword,
                futsalOpeningTime, futsalClosingTime, futsalPrice, futsalImage);
        FutsalAPI futsalAPI = Url.getInstance().create(FutsalAPI.class);
        Call<RegisterResponse> registerResponseCall = futsalAPI.registerFutsal(futsal);
        try{
            Response<RegisterResponse> registerResponse = registerResponseCall.execute();
            if (registerResponse.isSuccessful() &&
                    registerResponse.body().getStatus().equals("Registered Successfully!")){
                Url.token += registerResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean registerUser(String username, String address, String email, String phone, String password, String gender, String image) {

        Users users = new Users(username, address, email, phone, password, gender, image);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<RegisterResponse> registerResponseCall = usersAPI.registerUser(users);
        try{
            Response<RegisterResponse> registerResponse = registerResponseCall.execute();
            if (registerResponse.isSuccessful() &&
                    registerResponse.body().getStatus().equals("Registered Successfully!")){
                Url.token += registerResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }

}
