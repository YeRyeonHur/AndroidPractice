package com.kyh.knucommunity.util;

import com.kyh.knucommunity.model.DTODataGet;
import com.kyh.knucommunity.model.DTOUserList;
import com.kyh.knucommunity.model.DTODataPost;

import java.util.HashMap;

import retrofit2.*;
import retrofit2.http.*;

public interface MyRetrofitService { // Controller
    @GET("/")
    Call<DTODataGet> userNameGet(@Query("name") String name);

    @FormUrlEncoded // HashMap 으로 param 여러 개 넘기려면 필수
    @POST("/dataRequest")
    Call<DTODataPost> remoteDBDataPost(@FieldMap HashMap<String, Integer> params);

    @GET("/getUserList")
    Call<DTOUserList> userListGet();
}
