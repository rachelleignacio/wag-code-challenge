package com.rachelleignacio.wagcodechallenge.network;

import com.rachelleignacio.wagcodechallenge.domain.User;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class DataRepository {
    private StackoverflowApiService soApiService;

    public void initApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackoverflowApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        soApiService = retrofit.create(StackoverflowApiService.class);
    }

    public void getUsers(final Callback<SoResponseWrapper<User>> callback) {
        soApiService.getUserList().enqueue(callback);
    }
}
