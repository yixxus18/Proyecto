package com.example.proyecto.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyecto.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.proyecto.model.ApiResponse;
import com.example.proyecto.model.Feed;
import com.example.proyecto.adapter.FeedAdapter;
import com.example.proyecto.retrofit.ApiService;
import com.example.proyecto.view_model.FeedViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity5 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeedAdapter feedAdapter;
    private FeedViewModel feedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        feedAdapter = new FeedAdapter();
        recyclerView.setAdapter(feedAdapter);

        feedViewModel = new ViewModelProvider(this).get(FeedViewModel.class);
        observeFeedData();

        Switch switchControl = findViewById(R.id.switchControl);

        feedViewModel.getSwitchState().observe(this, switchOn -> {
            switchControl.setChecked(switchOn);
            switchControl.setOnCheckedChangeListener(null);
            switchControl.setChecked(switchOn);
            switchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int valueToSend = isChecked ? 1 : 0;
                feedViewModel.sendDataToServer(valueToSend);
            });
        });
        switchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            feedViewModel.setSwitchState(isChecked);
        });

    }

    private void observeFeedData() {
        feedViewModel.getFeedList().observe(this, feeds -> {
            feedAdapter.setFeeds(feeds);
            showToast("Datos actualizados");
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}