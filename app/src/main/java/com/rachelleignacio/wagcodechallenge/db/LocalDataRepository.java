package com.rachelleignacio.wagcodechallenge.db;

import android.content.Context;
import android.widget.Toast;

import com.rachelleignacio.wagcodechallenge.domain.User;
import com.rachelleignacio.wagcodechallenge.ui.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by rachelleignacio on 9/24/17.
 * Singleton class for interacting with the local database via Realm.
 */
public class LocalDataRepository {
    private static LocalDataRepository instance;
    private Realm realmInstance;
    private Context context;

    public static LocalDataRepository getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDataRepository(context);
        }
        return instance;
    }

    private LocalDataRepository(Context ctx) {
        context = ctx;
        initRealm();
    }

    public void initRealm() {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        realmInstance = Realm.getDefaultInstance();
    }

    public void getUsers(final MainActivityPresenter.View view) {
        final RealmResults<User> queryResults = realmInstance.where(User.class).findAllAsync();
        queryResults.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> users) {
                queryResults.removeAllChangeListeners();
                view.hideLoading();
                view.showUserList(new ArrayList<>(users));
            }
        });
    }

    public void refreshUsers(final List<User> newUserList) {
        final RealmResults<User> queryResults = realmInstance.where(User.class).findAllAsync();

        //RealmChangeListener is a callback that gets called when the async query completes
        queryResults.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> users) {
                queryResults.removeAllChangeListeners();
                if (users.size() > 0) {
                    deleteOldAndSaveNew(newUserList);
                } else {
                    saveUserList(newUserList);
                }
            }
        });

    }

    private void saveUserList(final List<User> newUserList) {
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(newUserList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() { /*do nothing*/ }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
                Toast.makeText(context, "Error saving users: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteOldAndSaveNew(final List<User> newUserList) {
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                //save the new user list if delete transaction is successful
                saveUserList(newUserList);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(context, "Error saving users: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void closeRealm() {
        realmInstance.removeAllChangeListeners();
        if (realmInstance.isInTransaction()) {
            realmInstance.cancelTransaction();
        }
        realmInstance.close();
    }
}
