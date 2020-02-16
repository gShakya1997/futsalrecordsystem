package com.futsalrecord.futsalinfosystem.url;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String base_url = "http://10.0.2.2:3007/"; //Emulator
//    public static final String base_url = "http://192.168.123.3:3007/"; //laptop
    public static String token = "Bearer ";
    public static String tokenUser = "Bearer ";
    public static String imagePath = base_url + "upload/";

    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
