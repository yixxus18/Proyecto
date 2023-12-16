package com.example.proyecto.retrofit;

import com.example.proyecto.model.ControlData;
import com.example.proyecto.model.Data;
import com.example.proyecto.response.LoginResponse;
import com.example.proyecto.model.ApiResponse;
import com.example.proyecto.model.UserLogin;
import com.example.proyecto.response.MyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface ApiService {

    @POST("/api/auth/register")
    Call<Data> postUser(@Body Data user);

    @POST(value = "/api/auth/login")
    Call<LoginResponse> loginUser(@Body UserLogin user);

    @GET("api/consumir")
    Call<ApiResponse> getFeedData();

    @POST("api/enviar")
    Call<MyResponse> controlLuces(@Body ControlData controlData);

}
