package com.example.assignment.Fragment;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment.Adapter.UsersAdapter;
import com.example.assignment.ApiServiceUser;
import com.example.assignment.Models.ApiResponseUser;
import com.example.assignment.Models.User;
import com.example.assignment.R;
import com.example.assignment.helpers.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersFragment extends Fragment {

    String url = API.BASE_URL_API;
    private String BASE_URL = url;
    String URLIMAGE = API.URL_IP;

    private Context context;

    private ImageView imageProfileImageView;

    private TextView usernameProfileTextView, emailProfileTextView,
            fullnameProfileTextView, changePasswordProfileTextView;
    private Button btnProfileButton;
    private String value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            value = bundle.getString("mUserName");
            // Sử dụng giá trị value
        } else {
            value = "Người dùng lỗi";
            // Xử lý trường hợp bundle có giá trị null
        }
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageProfileImageView = view.findViewById(R.id.imageProfileImageView);
        usernameProfileTextView = view.findViewById(R.id.usernameProfileTextView);
        emailProfileTextView = view.findViewById(R.id.emailProfileTextView);
        fullnameProfileTextView = view.findViewById(R.id.fullnameProfileTextView);
        changePasswordProfileTextView = view.findViewById(R.id.changePasswordProfileTextView);
        btnProfileButton = view.findViewById(R.id.btnProfileButton);


        Log.i("zzzz", value);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceUser apiServiceUser = retrofit.create(ApiServiceUser.class);
        Call<User> call = apiServiceUser.getUser(value);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    // Sử dụng đối tượng user để hiển thị thông tin của người dùng
                    assert user != null;
                    Glide.with(getContext())
                            .load("http://" + URLIMAGE + ":3000" + user.getAvatar())
                            .into(imageProfileImageView);

                    usernameProfileTextView.setText(user.getUsername());
                    emailProfileTextView.setText(user.getEmail());
                    fullnameProfileTextView.setText(user.getFullname());

                    Log.i("zzzz", user.toString());
                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

}