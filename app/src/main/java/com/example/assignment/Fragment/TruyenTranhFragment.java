package com.example.assignment.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment.Adapter.TruyenAdapter;
import com.example.assignment.Adapter.UsersAdapter;
import com.example.assignment.ApiServiceTruyen;
import com.example.assignment.ApiServiceUser;
import com.example.assignment.Models.ApiResponseTruyen;
import com.example.assignment.Models.ApiResponseUser;
import com.example.assignment.Models.Truyen;
import com.example.assignment.Models.User;
import com.example.assignment.R;
import com.example.assignment.helpers.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TruyenTranhFragment extends Fragment {

    String url = API.BASE_URL_API;
    private String BASE_URL = url;

    private RecyclerView truyenRecyclerView;// hiển thị lên recyclerview
    private TruyenAdapter truyenAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_truyen_tranh, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layoutT);

        truyenRecyclerView = view.findViewById(R.id.truyenRecyclerView);// hiển thị lên recyclerview
        truyenRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceTruyen apiGetService = retrofit.create(ApiServiceTruyen.class);
        Call<ApiResponseTruyen> call = apiGetService.getAllTruyen();

        call.enqueue(new Callback<ApiResponseTruyen>() {
            @Override
            public void onResponse(Call<ApiResponseTruyen> call, Response<ApiResponseTruyen> response) {
                if (response.isSuccessful()) {

                    ApiResponseTruyen arrayTruyen = response.body();
                    List<Truyen> truyens = arrayTruyen.getData();
                    // Xử lý dữ liệu ở đây

                    truyenAdapter = new TruyenAdapter(truyens,getContext());
                    truyenRecyclerView.setAdapter(truyenAdapter);
                    for (Truyen t : truyens) {
                        Log.i("zzzz", "msg: " + t.toString());
                    }

                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<ApiResponseTruyen> call, Throwable t) {
                // Xử lý lỗi
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Tải lại dữ liệu ở đây
                // ...

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiServiceTruyen apiGetService = retrofit.create(ApiServiceTruyen.class);
                Call<ApiResponseTruyen> call = apiGetService.getAllTruyen();

                call.enqueue(new Callback<ApiResponseTruyen>() {
                    @Override
                    public void onResponse(Call<ApiResponseTruyen> call, Response<ApiResponseTruyen> response) {
                        if (response.isSuccessful()) {

                            ApiResponseTruyen arrayTruyen = response.body();
                            List<Truyen> truyens = arrayTruyen.getData();
                            // Xử lý dữ liệu ở đây

                            truyenAdapter = new TruyenAdapter(truyens,getContext());
                            truyenRecyclerView.setAdapter(truyenAdapter);
                            for (Truyen t : truyens) {
                                Log.i("zzzz", "msg: " + t.toString());
                            }

                        } else {
                            // Xử lý lỗi
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponseTruyen> call, Throwable t) {
                        // Xử lý lỗi
                        Log.e("MainActivity", "Error: " + t.getMessage());
                    }
                });

                // Đánh dấu hoàn tất tải lại
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}