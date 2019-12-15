package com.example.newsfeed.utils;

import com.example.newsfeed.holdermodel.Response;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.example.newsfeed.utils.ResponseStatus.ERROR;
import static com.example.newsfeed.utils.ResponseStatus.LOADING;
import static com.example.newsfeed.utils.ResponseStatus.SUCCESS;

/**
 * Description:
 * Response holder class, so that UI can show either content or error UI based on type of response
 */
public class NewsApiResponse {

    public final ResponseStatus mStatus;

    @Nullable
    public final Response mData;

    @Nullable
    public final Throwable mError;

    private NewsApiResponse(ResponseStatus status, @Nullable Response data, @Nullable Throwable error) {
        mStatus = status;
        mData = data;
        mError = error;
    }

    public static NewsApiResponse loading() {
        return new NewsApiResponse(LOADING, null, null);
    }

    public static NewsApiResponse success(@NonNull Response data) {
        return new NewsApiResponse(SUCCESS, data, null);
    }

    public static NewsApiResponse error(@NonNull Throwable error) {
        return new NewsApiResponse(ERROR, null, error);
    }

}
