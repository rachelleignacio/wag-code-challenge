package com.rachelleignacio.wagcodechallenge.domain;

import android.content.Context;

import com.rachelleignacio.wagcodechallenge.db.LocalDataRepository;
import com.rachelleignacio.wagcodechallenge.network.RemoteDataRepository;
import com.rachelleignacio.wagcodechallenge.ui.MainActivityPresenter;

import java.util.List;

/**
 * Created by rachelleignacio on 9/24/17.
 * Class for determining whether data should be fetched locally or from the API.
 * This is a singleton the holds the only instances of both the local and remote data repos.
 */

public class DataRepository implements RemoteDataRepository.OnUsersRetrievedListener {
    private static DataRepository instance;
    private RemoteDataRepository remoteDataRepository;
    private LocalDataRepository localDataRepository;

    public static DataRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DataRepository();
            instance.remoteDataRepository = RemoteDataRepository.getInstance(instance);
            instance.localDataRepository = LocalDataRepository.getInstance(context);
        }
        return instance;
    }

    private DataRepository() {}

    public void getUsers(boolean isNetworkConnected, MainActivityPresenter.View view) {
        if (isNetworkConnected) {
            remoteDataRepository.getUsers(view);
        } else {
            localDataRepository.getUsers(view);
        }
    }

    @Override
    public void onUsersRetrieved(List<User> newUserList) {
        localDataRepository.refreshUsers(newUserList);
    }

    public void openLocalDatabase() {
        localDataRepository.initRealm();
    }

    public void closeLocalDatabase() {
        localDataRepository.closeRealm();
    }
}
