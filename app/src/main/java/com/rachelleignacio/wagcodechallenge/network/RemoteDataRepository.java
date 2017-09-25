package com.rachelleignacio.wagcodechallenge.network;

import android.widget.Toast;

import com.rachelleignacio.wagcodechallenge.domain.User;
import com.rachelleignacio.wagcodechallenge.ui.MainActivity;
import com.rachelleignacio.wagcodechallenge.ui.MainActivityPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rachelleignacio on 9/22/17.
 * Singleton class for interacting with the Stackoverflow API service.
 */
public class RemoteDataRepository {
    private StackoverflowApiService soApiService;
    private static RemoteDataRepository instance;
    private OnUsersRetrievedListener onUsersRetrievedListener;

    public static RemoteDataRepository getInstance(OnUsersRetrievedListener listener) {
        if (instance == null) {
            instance = new RemoteDataRepository();
            instance.onUsersRetrievedListener = listener;
        }
        return instance;
    }

    private RemoteDataRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackoverflowApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        soApiService = retrofit.create(StackoverflowApiService.class);
    }

    public void getUsers(final MainActivityPresenter.View view) {
        Callback<SoResponseWrapper<User>> usersCallback = new Callback<SoResponseWrapper<User>>() {
            @Override
            public void onResponse(Call<SoResponseWrapper<User>> call, Response<SoResponseWrapper<User>> response) {
                view.toggleLoading(false);
                if (response.isSuccessful()) {
                    List<User> users = response.body().items;
                    view.showUserList(users);
                    onUsersRetrievedListener.onUsersRetrieved(users);
                } else {
                    Toast.makeText((MainActivity) view, "Error fetching SO users: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SoResponseWrapper<User>> call, Throwable t) {
                t.printStackTrace();
            }
        };
        soApiService.getUserList().enqueue(usersCallback);
    }

    public interface OnUsersRetrievedListener {
        void onUsersRetrieved(List<User> users);
    }
}
