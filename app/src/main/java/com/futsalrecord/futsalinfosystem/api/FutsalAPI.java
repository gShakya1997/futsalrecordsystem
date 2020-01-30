package com.futsalrecord.futsalinfosystem.api;

import com.futsalrecord.futsalinfosystem.model.Customers;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.serverResponse.ImageResponse;
import com.futsalrecord.futsalinfosystem.serverResponse.RegisterResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FutsalAPI {
    //register
    @POST("futsal/register")
    Call<RegisterResponse> registerFutsal(@Body Futsal futsal);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadFutsalImage(@Part MultipartBody.Part image);

    //login
    @FormUrlEncoded
    @POST("futsal/login")
    Call<RegisterResponse> checkUser(@Field("futsalName") String futsalName, @Field("futsalPassword") String futsalPassword);

    //get futsalname and profile pic
    @GET("futsal/profile")
    Call<Futsal> getFutsalDetails(@Header("Authorization") String token);

    //add customers data
    @FormUrlEncoded
    @POST("customers")
    Call<Void> addCustomers(@Header("Authorization") String token,
                            @Field("customerFullname") String customerFullname,
                            @Field("customerEmail") String customerEmail,
                            @Field("customerPhoneNo") String customerPhoneNo,
                            @Field("customerGender") String customerGender,
                            @Field("customerAddress") String customerAddress);

    //get customers data
    @GET("futsal/customers")
    Call<List<Customers>> getCustomersDetails();



}
