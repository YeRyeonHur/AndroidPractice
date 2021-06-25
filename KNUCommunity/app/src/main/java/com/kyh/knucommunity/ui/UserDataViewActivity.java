package com.kyh.knucommunity.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kyh.knucommunity.viewmodel.UserDataViewViewModel;

public class UserDataViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDataViewViewModel.setUserListViewModel(getLayoutInflater());
        setContentView(UserDataViewViewModel.getInstance().getBinding().getRoot());
    }
}
