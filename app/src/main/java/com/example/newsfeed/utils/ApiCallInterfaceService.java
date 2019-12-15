package com.example.newsfeed.utils;

import com.example.newsfeed.holdermodel.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Description this class is a part of model used for network call.
 * We can add n number of methods to get different set of datas
 */
public interface ApiCallInterfaceService {

    @GET(NetworkConstants.SECTION)
    Observable<Response> getLatestPopularNewsFeed(@Query("api-key") String apiKey);
}
