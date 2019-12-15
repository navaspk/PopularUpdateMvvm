package com.example.newsfeed;

import com.example.newsfeed.holdermodel.Response;
import com.example.newsfeed.utils.ApiCallInterfaceService;

import io.reactivex.Observable;

/**
 * This is repository class useful for deciding data source.
 * Very helpful during future releases as through this we can achieve repository pattern.
 *
 * </p>
 *
 * In future we can decide from which source we have to take the data like either from cloud or
 * ROOM/SqLite database.
 */
public class NewsFeedRepository {

    private ApiCallInterfaceService mApiCallInterface;

    public NewsFeedRepository(ApiCallInterfaceService apiCallInterface) {
        this.mApiCallInterface = apiCallInterface;
    }

    /**
     * Network call to get popular news feeds
     *
     * @param apiKey
     * @return
     */
    public Observable<Response> getPopularNewsFeed(String apiKey) {
        return mApiCallInterface.getLatestPopularNewsFeed(apiKey);
    }

}