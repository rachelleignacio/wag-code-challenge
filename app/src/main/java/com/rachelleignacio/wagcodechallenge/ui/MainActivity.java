package com.rachelleignacio.wagcodechallenge.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rachelleignacio.wagcodechallenge.R;
import com.rachelleignacio.wagcodechallenge.domain.User;
import com.rachelleignacio.wagcodechallenge.network.DataRepository;
import com.rachelleignacio.wagcodechallenge.network.SoResponseWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {
    private DataRepository dataRepository = new DataRepository();
    private Callback<SoResponseWrapper<User>> usersCallback = new Callback<SoResponseWrapper<User>>() {
        @Override
        public void onResponse(Call<SoResponseWrapper<User>> call, Response<SoResponseWrapper<User>> response) {
            if (response.isSuccessful()) {
                showUserList(response.body().items);
            } else {
                Toast.makeText(MainActivity.this, "Error fetching SO users: " + response.message(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<SoResponseWrapper<User>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataRepository.initApiService();
        dataRepository.getUsers(usersCallback);
    }

    @Override
    public void showUserList(List<User> users) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UserAdapter(users));
    }
}
