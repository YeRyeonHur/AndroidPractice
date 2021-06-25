package com.kyh.knucommunity.util;

import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit { // IP PORT
    public static final String HOST = "10.0.2.2";
    public static final int PORT = 5000;

    static public MyRetrofit getInstance() {
        return new MyRetrofit();
    } // Stream Object

    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://" + HOST + ":" + PORT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    // Retrofit 객체 생성
    // url 설정
    // gson converter : JSON -> JavaClass
    // 생성

    MyRetrofitService service = retrofit.create(MyRetrofitService.class);
    // service 생성

    public MyRetrofitService getService() {
        return service;
    }
}
