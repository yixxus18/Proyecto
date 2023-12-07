package com.example.proyecto.retrofit;

import android.service.autofill.UserData;

import com.example.proyecto.model.Data;
import com.example.proyecto.model.LoginResponse;
import com.example.proyecto.model.ApiResponse;
import com.example.proyecto.model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface ApiService {

    @POST("/api/auth/register")
    Call<Data> postUser(@Body Data user);

    @POST(value = "/api/auth/login")
    Call<LoginResponse> loginUser(@Body UserLogin user);

    @GET("api/consumir")
    Call<ApiResponse> getFeedData();


}
