package com.rachelleignacio.wagcodechallenge.ui;

import android.widget.Toast;

import com.rachelleignacio.wagcodechallenge.domain.User;
import com.rachelleignacio.wagcodechallenge.network.DataRepository;
import com.rachelleignacio.wagcodechallenge.network.SoResponseWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public class MainActivityPresenterImpl implements MainActivityPresenter {
    private DataRepository dataRepository;
    private MainActivityPresenter.View view;

    public MainActivityPresenterImpl(MainActivityPresenter.View view, DataRepository dataRepository) {
        this.view = view;
        this.dataRepository = dataRepository;
    }

    @Override
    public void getUserList() {
        view.showLoading();
        Callback<SoResponseWrapper<User>> usersCallback = new Callback<SoResponseWrapper<User>>() {
            @Override
            public void onResponse(Call<SoResponseWrapper<User>> call, Response<SoResponseWrapper<User>> response) {
                view.hideLoading();
                if (response.isSuccessful()) {
                    view.showUserList(response.body().items);
                } else {
                    Toast.makeText((MainActivity) view, "Error fetching SO users: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SoResponseWrapper<User>> call, Throwable t) {
                t.printStackTrace();
            }
        };
        dataRepository.getUsers(usersCallback);
    }
}
