package com.futsalrecord.futsalinfosystem.api;

import com.futsalrecord.futsalinfosystem.model.Users;
import com.futsalrecord.futsalinfosystem.serverResponse.ImageResponse;
import com.futsalrecord.futsalinfosystem.serverResponse.RegisterResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UsersAPI {
    @POST("users/register")
    Call<RegisterResponse> registerUser(@Body Users users);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part image);
}
