package com.kyh.movieapp.handler;

import android.view.View;

import com.kyh.movieapp.adapter.SearchResultAdapter;

public interface SearchItemOnClickListener {
    void onItemClick(SearchResultAdapter.ViewHolder holder, View view, int position);
}
