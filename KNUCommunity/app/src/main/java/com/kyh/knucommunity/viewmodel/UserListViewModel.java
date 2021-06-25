package com.kyh.knucommunity.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.kyh.knucommunity.adapter.RecyclerViewAdapter;
import com.kyh.knucommunity.databinding.ActivityUserListBinding;
import com.kyh.knucommunity.handler.OnUserDataItemClickListener;
import com.kyh.knucommunity.model.DTOUser;
import com.kyh.knucommunity.model.RepositoryUserDataView;
import com.kyh.knucommunity.model.RepositoryUserList;
import com.kyh.knucommunity.ui.UserDataViewActivity;

@SuppressLint("StaticFieldLeak")
public class UserListViewModel {
    private static UserListViewModel userListViewModel;

    private ActivityUserListBinding binding;
    private Context context;

    private UserListViewModel(LayoutInflater inflater, Context context) {
        this.binding = ActivityUserListBinding.inflate(inflater);
        this.context = context;

        binding.recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                RepositoryUserList.requestData();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(RepositoryUserList.getData());
                adapter.setListener(new OnUserDataItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerViewAdapter.ViewHolder holder, View view, int position) {
                        Intent intent = new Intent(context, UserDataViewActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        DTOUser item = adapter.getItem(position);

                        RepositoryUserDataView.setIndex(item.getIndex());
                        RepositoryUserDataView.setName(item.getName());
                        RepositoryUserDataView.setTel(item.getTel());

                        context.startActivity(intent);
                    }
                });

                binding.recyclerView.setAdapter(adapter);
            }
        }.execute();
    }

    public static UserListViewModel getInstance() {
        return userListViewModel;
    }

    public static void setUserListViewModel(LayoutInflater inflater, Context context) {
        UserListViewModel.userListViewModel = new UserListViewModel(inflater, context);
    }

    public ActivityUserListBinding getBinding() {
        return binding;
    }
}