package com.futsalrecord.futsalinfosystem.api;

import com.futsalrecord.futsalinfosystem.model.CustomersUD;
import com.futsalrecord.futsalinfosystem.model.Events;
import com.futsalrecord.futsalinfosystem.model.Futsal;
import com.futsalrecord.futsalinfosystem.model.FutsalDetails;
import com.futsalrecord.futsalinfosystem.model.Users;
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

public interface UsersAPI {
    //registration
    @POST("users/register")
    Call<RegisterResponse> registerUser(@Body Users users);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part image);

    //login
    @FormUrlEncoded
    @POST("users/login")
    Call<RegisterResponse> checkUser(@Field("username") String username, @Field("password") String password);

    //Show futsal detail
    @GET("users/futsalList")
    Call<List<FutsalDetails>> getFutsalDetails(@Header("Authorization") String token);

    //Show futsal event
    @GET("users/eventList")
    Call<List<Events>> getEventDetail(@Header("Authorization") String token);

    //get user profile
    @GET("users/profile")
    Call<Users> getUserDetails(@Header("Authorization") String token);
}
