package com.kyh.movieapp.retrofit_service;

import com.kyh.movieapp.model.dtos.DTOSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SearchRetrofitService {
    String SEARCH_RESULT_GET_PATH = "/v1/search/movie.json";
    String CLIENT_ID_HEADER_KEY = "X-Naver-Client-Id", CLIENT_SECRET_HEADER_KEY = "X-Naver-Client-Secret";
    String QUERY_QUERY_KEY = "query", QUERY_DISPLAY_KEY = "display", QUERY_START_KEY = "start";
    @GET(SEARCH_RESULT_GET_PATH)
    Call<DTOSearchResult> searchResultGet(@Header(CLIENT_ID_HEADER_KEY) String clientID,
                                          @Header(CLIENT_SECRET_HEADER_KEY) String clientSecret,
                                          @Query(QUERY_QUERY_KEY) String query,
                                          @Query(QUERY_DISPLAY_KEY) int display,
                                          @Query(QUERY_START_KEY) int start);
}