package com.example.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.ApiServiceUser;
import com.example.assignment.Models.ApiResponseUser;
import com.example.assignment.Models.User;
import com.example.assignment.R;
import com.example.assignment.helpers.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    String url = API.BASE_URL_API;
    private String BASE_URL = url;
    private EditText userNameEditTextRegister, emailEditTextRegister,
            fullnameEditTextRegister, passWordEditTextRegister, passWordConfirmEditTextRegister;

    private Button registerButton,loginButtonRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.registerButton);
        loginButtonRe = findViewById(R.id.loginButtonRe);


        userNameEditTextRegister = findViewById(R.id.userNameEditTextRegister);
        emailEditTextRegister = findViewById(R.id.emailEditTextRegister);
        fullnameEditTextRegister = findViewById(R.id.fullnameEditTextRegister);
        passWordEditTextRegister = findViewById(R.id.passWordEditTextRegister);

        passWordConfirmEditTextRegister = findViewById(R.id.passWordConfirmEditTextRegister);

        userNameEditTextRegister.setText("khanhlinh");
        emailEditTextRegister.setText("khanhlinh@gmail.com");
        fullnameEditTextRegister.setText("ngo khanh linh");
        passWordEditTextRegister.setText("khanhlinh1234");
        passWordConfirmEditTextRegister.setText("khanhlinh1234");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passWordEditTextRegister.getText().toString().equals(passWordConfirmEditTextRegister.getText().toString())) {

                    ApiServiceUser apiServiceUser = retrofit.create(ApiServiceUser.class);
                    User newUser = new User();

                    newUser.setUsername(userNameEditTextRegister.getText().toString());
                    newUser.setEmail(emailEditTextRegister.getText().toString());
                    newUser.setFullname(fullnameEditTextRegister.getText().toString());
                    newUser.setPassword(passWordEditTextRegister.getText().toString());

                    Call<ApiResponseUser> call = apiServiceUser.createUser(newUser);
                    call.enqueue(new Callback<ApiResponseUser>() {
                        @Override
                        public void onResponse(Call<ApiResponseUser> call, Response<ApiResponseUser> response) {
                            if (response.isSuccessful()) {
                                ApiResponseUser apiResponse = response.body();

                                Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                                xoaText();

                                // ...
                            } else {
                                // Xử lý lỗi

                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponseUser> call, Throwable t) {
                            Log.e("MainActivity", "Error: " + t.getMessage());

                        }
                    });


                }else {
                    Toast.makeText(getApplicationContext(), "Mật khẩu và mật khẩu xác nhận không trùng khớp", Toast.LENGTH_SHORT).show();
                    passWordEditTextRegister.setText("");
                    passWordConfirmEditTextRegister.setText("");
                }
            }
        });

        loginButtonRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

    }
    public void xoaText(){
        userNameEditTextRegister.setText("");
        emailEditTextRegister.setText("");
        fullnameEditTextRegister.setText("");
        passWordEditTextRegister.setText("");
        passWordConfirmEditTextRegister.setText("");
    }
}