package com.kyh.knucommunity.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kyh.knucommunity.viewmodel.DataPostViewModel;

public class DataPostActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataPostViewModel.setDataPostViewModel(getLayoutInflater(), getApplicationContext());
        setContentView(DataPostViewModel.getInstance().getBinding().getRoot());
    }
}
