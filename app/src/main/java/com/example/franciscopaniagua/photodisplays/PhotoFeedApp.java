package com.example.franciscopaniagua.photodisplays;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.franciscopaniagua.photodisplays.PhotoFeed.di.DaggerPhotoFeedComponent;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.di.PhotoFeedComponent;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.di.PhotoFeedModule;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.PhotoFeedView;
import com.example.franciscopaniagua.photodisplays.lib.di.LibsModule;
import com.example.franciscopaniagua.photodisplays.model.MyDatabase;

public class PhotoFeedApp extends Application {

    private String DATABASE_NAME = "PhotoFeedDB";
    private MyDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        initDb();
    }

    private void initDb() {
        database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
    }

    public PhotoFeedComponent getPhotoFeedComponent(Activity activity, PhotoFeedView view){
        return DaggerPhotoFeedComponent.builder()
                .libsModule(new LibsModule(activity))
                .photoFeedModule(new PhotoFeedModule(view,database.photoDao()))
                .build();
    }
}
