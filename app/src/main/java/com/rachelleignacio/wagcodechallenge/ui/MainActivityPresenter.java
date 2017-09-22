package com.rachelleignacio.wagcodechallenge.ui;

import com.rachelleignacio.wagcodechallenge.domain.User;

import java.util.List;

/**
 * Created by rachelleignacio on 9/22/17.
 */

public interface MainActivityPresenter {
    interface View {
        void showUserList(List<User> users);
    }

    void getUserList();
}
