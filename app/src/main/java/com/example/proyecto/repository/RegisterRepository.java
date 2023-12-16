package com.example.proyecto.repository;

import android.util.Log;

import com.example.proyecto.model.Data;
import com.example.proyecto.response.RegisterResponse;
import com.example.proyecto.retrofit.ApiService;
import com.example.proyecto.retrofit.RetrofitRequest;
import com.google.gson.Gson;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterRepository {

    private MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();
    private ApiService apiService;

    public RegisterRepository() {
        apiService = RetrofitRequest.getRetrofitInstance().create(ApiService.class);
    }

    public MutableLiveData<Boolean> getRegistrationStatus() {
        return registrationStatus;
    }

    public void registerUser(String email, String password, String name) {
        Data user = new Data();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        Log.d("RegisterRepository", "Datos del usuario a enviar: " + user.toString());

        Call<Data> call = apiService.postUser(user);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (!response.isSuccessful()) {
                    handleErrorResponse(response);
                } else {
                    registrationStatus.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("RegisterRepository", "Error al realizar la llamada a la API: " + t.getMessage());
                registrationStatus.postValue(false);
            }
        });
    }

    private void handleErrorResponse(Response<Data> response) {
        int statusCode = response.code();
        Log.e("RegisterRepository", "Código de estado HTTP: " + statusCode);
        registrationStatus.postValue(false);

        try {
            if (response.errorBody() != null) {
                String errorBody = response.errorBody().string();
                Log.e("RegisterRepository", "Error en la respuesta de la API: " + errorBody);


                Gson gson = new Gson();
                RegisterResponse errorResponse = gson.fromJson(errorBody, RegisterResponse.class);


                String errorMessage = "";
                if (errorResponse != null && errorResponse.getData() != null) {
                    errorMessage = errorResponse.getData().getPassword();

                }


                Log.e("RegisterRepository", "Error en la respuesta de la API: " + errorMessage);
            }
        } catch (Exception e) {
            Log.e("RegisterRepository", "Error al manejar la respuesta de error: " + e.getMessage());
            registrationStatus.postValue(false);
        }
    }
}
