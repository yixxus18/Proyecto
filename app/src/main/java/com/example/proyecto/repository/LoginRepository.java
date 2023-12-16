package com.example.proyecto.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.proyecto.model.UserLogin;
import com.example.proyecto.retrofit.ApiService;
import com.example.proyecto.retrofit.RetrofitRequest;
import com.example.proyecto.response.LoginResponse;
import com.google.gson.Gson;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private ApiService apiService;
    private SharedPreferences sharedPreferences;

    public LoginRepository() {
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


        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");
        Log.d("SharedPreferences", "Email: " + savedEmail);
        Log.d("SharedPreferences", "Password: " + savedPassword);
    }
}
