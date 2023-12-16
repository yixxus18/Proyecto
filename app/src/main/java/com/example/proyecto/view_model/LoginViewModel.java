package com.example.proyecto.view_model;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    private LoginRepository loginRepository = new LoginRepository();

    public MutableLiveData<Boolean> getLoginStatus() {
        return loginRepository.getLoginStatus();
    }

    public void loginUser(String email, String password, Context context) {
        loginRepository.loginUser(email, password, context);
    }
}
