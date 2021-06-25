package com.kyh.knucommunity.handler;

import android.view.View;

import com.kyh.knucommunity.adapter.RecyclerViewAdapter;

public interface OnUserDataItemClickListener {
    default void onItemClick(RecyclerViewAdapter.ViewHolder holder, View view, int position) {

    }
}
