package com.kyh.movieapp.retrofit;

import android.util.Log;

import com.kyh.movieapp.retrofit_service.SearchRetrofitService;

import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRetrofit {
    public static final String ROOT = "https://openapi.naver.com";

    private retrofit2.Retrofit retrofit;
    private SearchRetrofitService service;

    private SearchRetrofit() {
        Log.d("RetrofitData", ROOT + SearchRetrofitService.SEARCH_RESULT_GET_PATH);

        this.retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.service = retrofit.create(SearchRetrofitService.class);
    }

    public static SearchRetrofit getInstance() {
        return new SearchRetrofit();
    }

    public SearchRetrofitService getService() {
        return service;
    }
}
