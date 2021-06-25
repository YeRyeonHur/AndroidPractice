package com.kyh.knucommunity.model;

import android.util.Log;

import com.kyh.knucommunity.util.MyRetrofit;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class RepositoryUserList {
    private static Call<DTOUserList> res;
    private static DTOUserList body;
    private static ArrayList<DTOUser> data;

    public static void requestData(){
        res = MyRetrofit.getInstance().getService().userListGet();
        try {
            Response<DTOUserList> response = res.execute();

            if(response.isSuccessful()) {
                Log.d("RetrofitSuccess", response.message());
                body = response.body();
                data = body.getData();
            } else {
                switch (response.code()) {
                    default: Log.d("RetrofitError", response.message());
                }
            }
        } catch (IOException ignore) {
            Log.d("RetrofitFailure", "Retrofit is failed with IOException");
        } finally {

        }
    }

    public static ArrayList<DTOUser> getData() {
        return data;
    }
}
