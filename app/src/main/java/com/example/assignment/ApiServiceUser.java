package com.example.assignment;

import com.example.assignment.Models.ApiResponseUser;
import com.example.assignment.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServiceUser {
    @GET("users")
    Call<ApiResponseUser> getAllUsers();

    @GET("user/{username}")
    Call<User> getUser(@Path("username") String username);

    @POST("users")
    Call<ApiResponseUser> createUser(@Body User user);

    @FormUrlEncoded
    @POST("userslogin")
    Call<ApiResponseUser> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @PUT("users/{id}")
    Call<ApiResponseUser> updateUser(@Path("id") String id, @Body User user);

    @DELETE("users/{id}")
    Call<ApiResponseUser> deleteUser(@Path("id") String id);
}
