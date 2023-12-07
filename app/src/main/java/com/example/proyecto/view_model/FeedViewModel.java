package com.example.proyecto.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto.model.ApiResponse;
import com.example.proyecto.model.Feed;
import com.example.proyecto.retrofit.ApiService;
import com.example.proyecto.retrofit.RetrofitRequest;

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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        // Ejecutar la solicitud cada 10 segundos
        if (timer == null) {
            timer = new Timer();
            timer.schedule(timerTask, 0, 10000); // 10 segundos (10000 milisegundos)
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
}


