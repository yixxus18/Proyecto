package com.example.proyecto.view_model;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto.model.Data;
import com.example.proyecto.response.RegisterResponse;
import com.example.proyecto.retrofit.ApiService;
import com.example.proyecto.retrofit.RetrofitRequest;
import com.example.proyecto.view.MainActivity2;
import com.google.gson.Gson;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();
    private ApiService apiService;

    public RegisterViewModel() {
        apiService = RetrofitRequest.getRetrofitInstance().create(ApiService.class);
    }

    public LiveData<Boolean> getRegistrationStatus() {
        return registrationStatus;
    }

    public void registerUser(String email, String password, String name) {

        Data user = new Data();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        Log.d("RegisterViewModel", "Datos del usuario a enviar: " + user.toString());

        Call<Data> call = apiService.postUser(user);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (!response.isSuccessful()) {
                    int statusCode = response.code();
                    Log.e("RegisterViewModel", "Código de estado HTTP: " + statusCode);
                    registrationStatus.postValue(false);

                    try {
                        if (response.errorBody() != null) {
                            String errorBody = response.errorBody().string();
                            Log.e("RegisterViewModel", "Error en la respuesta de la API: " + errorBody);



                            Gson gson = new Gson();
                            RegisterResponse errorResponse = gson.fromJson(errorBody, RegisterResponse.class);


                            String errorMessage = "";
                            if (errorResponse != null && errorResponse.getData() != null) {
                                errorMessage = errorResponse.getData().getPassword();
                            }
                            Log.e("RegisterViewModel", "Error en la respuesta de la API: " + errorMessage);
                        }
                    } catch (IOException e) {
                        Log.e("RegisterViewModel", "Error al obtener el cuerpo de error: " + e.getMessage());
                        registrationStatus.postValue(false);
                    }
                } else {
                    registrationStatus.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

                Log.e("RegisterViewModel", "Error al realizar la llamada a la API: " + t.getMessage());
                registrationStatus.postValue(false);
            }
        });

    }
}

