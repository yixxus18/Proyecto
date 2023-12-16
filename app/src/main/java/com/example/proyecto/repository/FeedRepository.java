package com.example.proyecto.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyecto.model.ApiResponse;
import com.example.proyecto.model.ControlData;
import com.example.proyecto.model.Feed;
import com.example.proyecto.response.MyResponse;
import com.example.proyecto.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedRepository {

    private ApiService apiService;

    public FeedRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<List<Feed>> getFeedData() {
        MutableLiveData<List<Feed>> feedLiveData = new MutableLiveData<>();

        apiService.getFeedData().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null) {
                        List<Feed> feeds = apiResponse.getData().getFeeds();
                        feedLiveData.setValue(feeds);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return feedLiveData;
    }

    public LiveData<MyResponse> controlLuces(ControlData controlData) {
        MutableLiveData<MyResponse> controlLucesLiveData = new MutableLiveData<>();

        apiService.controlLuces(controlData).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.isSuccessful()) {
                    MyResponse myResponse = response.body();
                    controlLucesLiveData.setValue(myResponse);
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return controlLucesLiveData;
    }

}
