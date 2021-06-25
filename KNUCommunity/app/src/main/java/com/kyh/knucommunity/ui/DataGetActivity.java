package com.kyh.knucommunity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kyh.knucommunity.viewmodel.DataGetViewModel;

public class DataGetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataGetViewModel.setDataGetViewModel(getLayoutInflater(), getApplicationContext());
        setContentView(DataGetViewModel.getInstance().getBinding().getRoot());
    }
}