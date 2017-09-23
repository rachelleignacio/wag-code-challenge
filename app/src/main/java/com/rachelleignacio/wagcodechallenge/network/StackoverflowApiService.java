package com.rachelleignacio.wagcodechallenge.network;

import com.rachelleignacio.wagcodechallenge.domain.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public interface StackoverflowApiService {
    String baseUrl = "https://api.stackexchange.com";

    @GET("/2.2/users?site=stackoverflow")
    Call<SoResponseWrapper<User>> getUserList();
}
