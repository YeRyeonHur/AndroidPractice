package com.kyh.movieapp.model.repositories;

import com.kyh.movieapp.model.dtos.DTOSearchResult;
import com.kyh.movieapp.retrofit.SearchRetrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RepositorySearch {
    public static final String CLIENT_ID_HEADER_VALUE = "5OrErS_NDotK04MetdBb", CLIENT_SECRET_HEADER_VALUE = "nx2_PqqraC";
    public static final int RETROFIT_FAILURE_CODE = -1;
    public static final int DISPLAY_PER_PAGE = 10;

    private static Call<DTOSearchResult> res;
    private static Response<DTOSearchResult> response;
    private static int responseCode = RETROFIT_FAILURE_CODE;
    private static DTOSearchResult data;

    public static boolean requestData(String query, int start) throws IOException {
        res = SearchRetrofit.getInstance().getService().searchResultGet(CLIENT_ID_HEADER_VALUE, CLIENT_SECRET_HEADER_VALUE, query, DISPLAY_PER_PAGE, start);

        try {
            response = res.execute();
            data = response.body();
        } finally {
            if(response != null) {
                responseCode = response.code();
                return response.isSuccessful();
            }

            return false;
        }
    }

    public static Response<DTOSearchResult> getResponse() {
        return response;
    }

    public static int getResponseCode() {
        return responseCode;
    }

    public static DTOSearchResult getData() {
        return data;
    }

    public static String getResponseMessage() {
        if(response != null) {
            return response.message();
        } else {
            return "Response Null";
        }
    }
}
