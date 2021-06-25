package com.kyh.knucommunity.model;

import android.util.Log;

import com.kyh.knucommunity.util.MyRetrofit;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

public class RepositoryDataPost {
    private static Call<DTODataPost> res;
    private static DTODataPost data;

    public static void requestData(HashMap<String, Integer> params) {
        res = MyRetrofit.getInstance().getService().remoteDBDataPost(params);

        try {
            Response<DTODataPost> response = res.execute();

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

    public static DTODataPost getData() {
        return data;
    }
}
