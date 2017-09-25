package com.rachelleignacio.wagcodechallenge.ui;


import com.rachelleignacio.wagcodechallenge.domain.DataRepository;

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
        view.toggleLoading(true);
        dataRepository.getUsers(((MainActivity)view).isNetworkConnected(), view);
    }
}
