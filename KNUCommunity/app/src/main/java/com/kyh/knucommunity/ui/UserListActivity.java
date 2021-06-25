package com.kyh.knucommunity.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kyh.knucommunity.viewmodel.UserListViewModel;

public class UserListActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserListViewModel.setUserListViewModel(getLayoutInflater(), getApplicationContext());
        setContentView(UserListViewModel.getInstance().getBinding().getRoot());
    }
}
