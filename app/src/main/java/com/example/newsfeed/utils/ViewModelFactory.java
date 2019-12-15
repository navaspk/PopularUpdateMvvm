package com.example.newsfeed.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsfeed.NewsFeedViewModel;
import com.example.newsfeed.NewsFeedRepository;

import javax.inject.Inject;

/**
 * Description
 * Factory class to get corresponding ViewModel class, this is part of #VIEWMODEL
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private NewsFeedRepository mRepository;

    @Inject
    public ViewModelFactory(NewsFeedRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(NewsFeedViewModel.class)) {
            return (T) new NewsFeedViewModel(mRepository);
        }
        throw new IllegalArgumentException("Unknown class");
    }
}
