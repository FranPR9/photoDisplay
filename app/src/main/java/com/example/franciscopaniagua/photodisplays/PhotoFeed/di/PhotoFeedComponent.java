package com.example.franciscopaniagua.photodisplays.PhotoFeed.di;

import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedPresenter;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.adapter.PhotoFeedAdapter;
import com.example.franciscopaniagua.photodisplays.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by FranciscoPR on 29/10/16.
 */
@Singleton
@Component(modules = {LibsModule.class,PhotoFeedModule.class})
public interface PhotoFeedComponent {

    PhotoFeedPresenter getPhotoFeedPresenter();
    PhotoFeedAdapter getPhotoFeedAdapter();
    //GamesAdapter getGamesAdapter();
}
