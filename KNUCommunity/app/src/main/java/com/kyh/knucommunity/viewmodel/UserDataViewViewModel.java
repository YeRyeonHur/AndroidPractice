package com.kyh.knucommunity.viewmodel;

import android.view.LayoutInflater;

import com.kyh.knucommunity.databinding.ActivityUserDataViewBinding;
import com.kyh.knucommunity.model.RepositoryUserDataView;

public class UserDataViewViewModel {
    private static UserDataViewViewModel userListViewModel;

    private ActivityUserDataViewBinding binding;

    private UserDataViewViewModel(LayoutInflater inflater) {
        this.binding = ActivityUserDataViewBinding.inflate(inflater);

        binding.userViewIndex.setText(RepositoryUserDataView.getIndex());
        binding.userViewName.setText(RepositoryUserDataView.getName());
        binding.userViewTel.setText(RepositoryUserDataView.getTel());
    }

    public static UserDataViewViewModel getInstance() {
        return  userListViewModel;
    }

    public static void setUserListViewModel(LayoutInflater inflater) {
        UserDataViewViewModel.userListViewModel = new UserDataViewViewModel(inflater);
    }

    public ActivityUserDataViewBinding getBinding() {
        return this.binding;
    }
}
