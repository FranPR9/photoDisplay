package com.example.franciscopaniagua.photodisplays.model;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

@Database(entities = {PhotoFeedItem.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();
}
