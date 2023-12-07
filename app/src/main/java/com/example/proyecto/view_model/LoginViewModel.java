package com.example.proyecto.view_model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.proyecto.model.LoginResponse;
import com.example.proyecto.model.UserLogin;
import com.example.proyecto.retrofit.ApiService;
import com.example.proyecto.retrofit.RetrofitRequest;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private ApiService apiService;
    private SharedPreferences sharedPreferences;

    public LoginViewModel() {
        apiService = RetrofitRequest.getRetrofitInstance().create(ApiService.class);
    }

    public MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public void loginUser(String email, String password, Context context) {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        UserLogin user = new UserLogin(email, password);

        Call<LoginResponse> call = apiService.loginUser(user);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    // Imprimir el JSON de la respuesta en la consola
                    Gson gson = new Gson();
                    String jsonResponse = gson.toJson(loginResponse);
                    Log.d("APIResponse", "Login Response JSON: " + jsonResponse);

                    saveCredentials(email, password, context);
                    String token = loginResponse.getAccessToken();
                    saveToken(token, context);
                    loginStatus.postValue(true);
                } else {
                    loginStatus.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginStatus.postValue(false);
            }
        });
    }

    private void saveToken(String token, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();

        String savedToken = sharedPreferences.getString("token", "");
        Log.d("SharedPreferences", "Token: " + savedToken);
    }

    private void saveCredentials(String email, String password, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();

        // Imprimir el valor del correo y contraseña en la consola
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");
        Log.d("SharedPreferences", "Email: " + savedEmail);
        Log.d("SharedPreferences", "Password: " + savedPassword);
    }

}

