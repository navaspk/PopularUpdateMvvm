package com.example.newsfeed;

import android.app.Application;
import android.content.Context;

import com.example.newsfeed.DependancyInj.AppComponent;
import com.example.newsfeed.DependancyInj.AppModule;
import com.example.newsfeed.DependancyInj.DaggerAppComponent;
import com.example.newsfeed.DependancyInj.UtilsModule;

/**
 * Description:
 * Application class for initializing AppComponent using dagger
 */
public class NewsFeedApplication extends Application {
    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
