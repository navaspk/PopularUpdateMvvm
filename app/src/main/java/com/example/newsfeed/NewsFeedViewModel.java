package com.example.newsfeed;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsfeed.utils.NewsApiResponse;
import com.example.newsfeed.utils.NetworkConstants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Part of #VIEWMODEL
 * This is a ViewModel class designed for UI data managing and data storing, manipulating.
 * This class help us survive the data from any configuration change like orientation etc.
 *
 * </P>
 *
 * Here we are taken care for repository method call to initiate network call
 */
public class NewsFeedViewModel extends ViewModel {

    private NewsFeedRepository mRepository;
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private final MutableLiveData<NewsApiResponse> mResponseLiveData = new MutableLiveData<>();

    public NewsFeedViewModel(NewsFeedRepository repository) {
        this.mRepository = repository;
    }

    public MutableLiveData<NewsApiResponse> getNewsResponse() {
        return mResponseLiveData;
    }

    /**
     * Direct call to repository from there this will initiate network call
     * for getting popular news
     */
    public void getPopularNews() {
        mDisposables.add(mRepository.getPopularNewsFeed(NetworkConstants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> mResponseLiveData.setValue(NewsApiResponse.loading()))
                .subscribe(
                        // When result obtained & updating with live data, so that ui
                        // will be automatically notify by invoking onChanged() only when UI is
                        // active state
                        result -> mResponseLiveData.setValue(NewsApiResponse.success(result)),
                        // When any error happened it will notify the user by showing toast
                        throwable ->
                            mResponseLiveData.setValue(NewsApiResponse.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        mDisposables.clear();
    }
}
