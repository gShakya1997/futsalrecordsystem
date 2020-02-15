package com.study.futsalwear;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FutsalAPI {
    //login
    @FormUrlEncoded
    @POST("futsal/login")
    Call<RegisterResponse> checkUser(@Field("futsalName") String futsalName,
                                     @Field("futsalPassword") String futsalPassword);
}
