package com.example.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.ApiServiceUser;
import com.example.assignment.Fragment.UsersFragment;
import com.example.assignment.MainActivity;
import com.example.assignment.Models.ApiResponseUser;
import com.example.assignment.Models.User;
import com.example.assignment.R;
import com.example.assignment.helpers.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText userNameEditText, passWordEditText;
    private CheckBox checkBox;
    private Button loginButton;
    private TextView registerTextView;

    String url = API.BASE_URL_API;
    private String BASE_URL = url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = findViewById(R.id.userNameEditTextLogin);
        passWordEditText = findViewById(R.id.passWordEditTextLogin);

        checkBox = findViewById(R.id.checkBox);
        loginButton = findViewById(R.id.loginButtonLogin);

        registerTextView = findViewById(R.id.registerTextViewLogin);

        setTaiKhoanMatKhauTest();

        String username = userNameEditText.getText().toString();
        String passwork = passWordEditText.getText().toString();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = userNameEditText.getText().toString();
                String password = passWordEditText.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiServiceUser apiServiceUser = retrofit.create(ApiServiceUser.class);
                Call<ApiResponseUser> call = apiServiceUser.loginUser(username, password);

                call.enqueue(new Callback<ApiResponseUser>() {
                    @Override
                    public void onResponse(Call<ApiResponseUser> call, Response<ApiResponseUser> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("mUserName", username);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Thông tin tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            passWordEditText.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponseUser> call, Throwable t) {
                        Log.e("MainActivity", "Error: " + t.getMessage());
                    }
                });


            }
        });


        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });
    }
    public void setTaiKhoanMatKhauTest(){
        userNameEditText.setText("tunganh");
        passWordEditText.setText("tung1234");

    }
}