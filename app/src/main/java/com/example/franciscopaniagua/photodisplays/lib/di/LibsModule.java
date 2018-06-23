package com.example.franciscopaniagua.photodisplays.lib.di;

import android.app.Activity;

import com.example.franciscopaniagua.photodisplays.lib.EventBus;
import com.example.franciscopaniagua.photodisplays.lib.GlideImageLoader;
import com.example.franciscopaniagua.photodisplays.lib.GreenRobotEventBus;
import com.example.franciscopaniagua.photodisplays.lib.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by franpr9.
 */
@Module
public class LibsModule {
    Activity activity;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Activity activity) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (activity != null) {
            imageLoader.setLoaderContext(activity);
        }
        return imageLoader;
    }


    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }

}
