package com.kyh.knucommunity.model;

import android.util.Log;

import com.kyh.knucommunity.util.MyRetrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RepositoryDataGet {
    private static Call<DTODataGet> res;
    private static DTODataGet data;

    public static void requestData(String userName) {
        res = MyRetrofit.getInstance().getService().userNameGet(userName);

        try {
            Response<DTODataGet> response = res.execute();

            if(response.isSuccessful()) {
                Log.d("RetrofitSuccess", response.message());
                data = response.body();
            } else {
                switch (response.code()) {
                    default: Log.d("RetrofitError", "Unhandled : " + response.message());
                }
            }
        } catch (IOException ignore) {
            Log.d("RetrofitFailure", "Retrofit is failed with IOException");
        } finally {

        }
    }

    public static DTODataGet getData() {
        return data;
    }
}
