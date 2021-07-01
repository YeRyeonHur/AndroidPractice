package com.kyh.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kyh.movieapp.viewmodel.SearchViewModel;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchViewModel.setSearchViewModel(getLayoutInflater(), getApplicationContext(), this);
        setContentView(SearchViewModel.getInstance().getBinding().getRoot());
    }
}