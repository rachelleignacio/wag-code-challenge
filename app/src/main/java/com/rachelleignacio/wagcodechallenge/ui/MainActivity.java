package com.rachelleignacio.wagcodechallenge.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rachelleignacio.wagcodechallenge.R;
import com.rachelleignacio.wagcodechallenge.domain.DataRepository;
import com.rachelleignacio.wagcodechallenge.domain.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {
    private MainActivityPresenter presenter;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataRepository = DataRepository.getInstance(this);
        presenter = new MainActivityPresenterImpl(this, dataRepository);
        presenter.getUserList();
    }

    @Override
    public void onStart() {
        super.onStart();
        dataRepository.openLocalDatabase();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataRepository.closeLocalDatabase();
    }

    @Override
    public void showUserList(List<User> users) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserAdapter(users));
    }

    @Override
    public void toggleLoading(boolean isLoading) {
        showOfflineMessage(false);
        if (isLoading) {
            findViewById(R.id.loading_spinner).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.loading_spinner).setVisibility(View.GONE);
        }
    }

    @Override
    public void showOfflineMessage(boolean isOffline) {
        if (isOffline) {
            findViewById(R.id.offline_message).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.offline_message).setVisibility(View.GONE);
        }
    }

    public boolean isNetworkConnected() {
        NetworkInfo networkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
