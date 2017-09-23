package com.rachelleignacio.wagcodechallenge.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.rachelleignacio.wagcodechallenge.R;
import com.rachelleignacio.wagcodechallenge.domain.User;
import com.rachelleignacio.wagcodechallenge.network.DataRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {
    private MainActivityPresenter presenter;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataRepository = new DataRepository();
        dataRepository.initApiService();
        presenter = new MainActivityPresenterImpl(this, dataRepository);
        presenter.getUserList();
    }

    @Override
    public void showUserList(List<User> users) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserAdapter(users));
    }

    @Override
    public void showLoading() {
        findViewById(R.id.loading_spinner).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.loading_spinner).setVisibility(View.GONE);
    }
}
