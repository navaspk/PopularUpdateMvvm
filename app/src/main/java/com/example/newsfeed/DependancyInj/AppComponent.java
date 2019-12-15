package com.example.newsfeed.DependancyInj;

import com.example.newsfeed.NewsFeedActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component creation with modules
 */
@Singleton
@Component(modules = {AppModule.class, UtilsModule.class})
public interface AppComponent {

    void doInjection(NewsFeedActivity activity);

}
