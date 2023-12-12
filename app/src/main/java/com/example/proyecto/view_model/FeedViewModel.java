package com.example.proyecto.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto.model.ApiResponse;
import com.example.proyecto.model.ControlData;
import com.example.proyecto.model.Feed;
import com.example.proyecto.response.MyResponse;
import com.example.proyecto.retrofit.ApiService;
import com.example.proyecto.retrofit.RetrofitRequest;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto.model.ApiResponse;
import com.example.proyecto.model.Feed;
import com.example.proyecto.retrofit.ApiService;
import com.google.gson.Gson;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FeedViewModel extends ViewModel {

    private MutableLiveData<List<Feed>> feedListLiveData = new MutableLiveData<>();
    private ApiService apiService;
    private Timer timer;

    public LiveData<List<Feed>> getFeedList() {
        if (feedListLiveData.getValue() == null) {
            fetchDataFromApi();
        }
        return feedListLiveData;
    }

    private void fetchDataFromApi() {
        if (apiService == null) {
            apiService = RetrofitRequest.getRetrofitInstance().create(ApiService.class);
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Call<ApiResponse> call = apiService.getFeedData();
                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            ApiResponse apiResponse = response.body();
                            if (apiResponse != null && apiResponse.getData() != null) {
                                List<Feed> feeds = apiResponse.getData().getFeeds();
                                feedListLiveData.postValue(feeds);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        };

        if (timer == null) {
            timer = new Timer();
            timer.schedule(timerTask, 0, 9000);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Detener el Timer cuando se elimina el ViewModel
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private MutableLiveData<Boolean> switchState = new MutableLiveData<>();

    public LiveData<Boolean> getSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean state) {
        switchState.postValue(state);
    }

    public void sendDataToServer(boolean switchOn) {
        if (switchOn) {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    sendDataToServer(3950);
                }
            };

            if (timer == null) {
                timer = new Timer();
                timer.schedule(timerTask, 0, 9000);
            }
        } else {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    private void sendDataToServer(int value) {


        Retrofit retrofit = RetrofitRequest.getRetrofitInstance();
        ApiService apiService = retrofit.create(ApiService.class);
        ControlData controlData = new ControlData(value);

        Call<MyResponse> call = apiService.controlLuces(controlData);
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.isSuccessful()) {
                    MyResponse myResponse = response.body();
                    Log.d("APIResponse", "Success: " + myResponse.getMessage());
                } else {
                    // Manejar error
                    try {
                        Gson gson = new Gson();
                        String errorBody = response.errorBody().string();
                        Log.e("APIResponse", "Error: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                // Manejar fallos
                Log.e("APIResponse", "Request failed: " + t.getMessage());
            }
        });
    }

}


