package com.example.assignment;

import com.example.assignment.Models.ApiResponseTruyen;
import com.example.assignment.Models.ApiResponseUser;
import com.example.assignment.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServiceTruyen {

    @GET("truyen")
    Call<ApiResponseTruyen> getAllTruyen();

    @GET("truyenten")
    Call<ApiResponseTruyen> getAllTruyenTen();

    @GET("truyennoidung")
    Call<ApiResponseTruyen> getAllTruyenNoiDung();

//    @POST("truyen")
//    Call<ApiResponseUser> createUser(@Body User user);
//
//    @PUT("users/{id}")
//    Call<ApiResponseUser> updateUser(@Path("id") String id, @Body User user);
//
//    @DELETE("users/{id}")
//    Call<ApiResponseUser> deleteUser(@Path("id") String id);
}
