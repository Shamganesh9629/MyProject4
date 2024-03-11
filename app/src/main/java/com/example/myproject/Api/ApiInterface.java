package com.example.myproject.Api;

import androidx.annotation.RequiresPermission;
import androidx.room.Delete;

import com.example.myproject.RoomDataBase.Registration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {
    @POST("shamganesh") // API endpoint for inserting data
    Call<Registration> createPost(@Body Registration registration); // Use Void if no response is expected
    @GET("shamganesh")
    Call<List<Registration>> getAllRegistration();
    @Delete
    Call<List<Registration>> deleteRegistration();
}
