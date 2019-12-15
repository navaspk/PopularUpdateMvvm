package com.example.newsfeed.DependancyInj;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module for proving context
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }
}
