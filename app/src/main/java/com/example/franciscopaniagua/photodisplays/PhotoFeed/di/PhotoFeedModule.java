package com.example.franciscopaniagua.photodisplays.PhotoFeed.di;

import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedInteractor;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedInteractorImpl;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedPresenter;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedPresenterImpl;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedRepository;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedRespositoryImpl;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.PhotoFeedView;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.adapter.PhotoFeedAdapter;
import com.example.franciscopaniagua.photodisplays.api.PhotoFeedClient;
import com.example.franciscopaniagua.photodisplays.api.PhotoFeedService;
import com.example.franciscopaniagua.photodisplays.lib.EventBus;
import com.example.franciscopaniagua.photodisplays.lib.ImageLoader;
import com.example.franciscopaniagua.photodisplays.model.PhotoDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Created by FranciscoPR on 29/10/16.
 */
@Module
public class PhotoFeedModule {

    PhotoFeedView view;
    PhotoDao photoDao;
    //OnGameListener listener;

    public PhotoFeedModule(PhotoFeedView view, PhotoDao photoDao) {
        this.view = view;
        this.photoDao = photoDao;
    }

    @Singleton
    @Provides
    PhotoFeedPresenter providesPhotoFeedPresenter(EventBus eventBus, PhotoFeedInteractor indexInteractor){
        return  new PhotoFeedPresenterImpl(indexInteractor,view,eventBus);
    }

    @Singleton
    @Provides
    PhotoFeedInteractor providesPhotoFeedInteractor(PhotoFeedRepository repository){
        return new PhotoFeedInteractorImpl(repository);
    }

    @Singleton
    @Provides
    PhotoFeedRepository providesPhotoFeedRepository(EventBus eventBus, PhotoFeedService feedService){
        return new PhotoFeedRespositoryImpl(eventBus,feedService,photoDao);
    }

    @Singleton
    @Provides
    PhotoFeedService providesFeedService(){
        PhotoFeedService feedService = new PhotoFeedClient().getPhotoFeedService();
        return feedService;
    }

    @Singleton
    @Provides
    PhotoFeedAdapter providesPhotoFeedAdapter(ImageLoader imageLoader){
        return new PhotoFeedAdapter(imageLoader);
    }

}
