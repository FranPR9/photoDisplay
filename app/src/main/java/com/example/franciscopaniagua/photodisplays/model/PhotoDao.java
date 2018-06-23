package com.example.franciscopaniagua.photodisplays.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photo")
    List<PhotoFeedItem> getAll();

    @Insert(onConflict = REPLACE)
    void insertAll(List<PhotoFeedItem> photos);

    @Insert(onConflict = REPLACE)
    void save(PhotoFeedItem photo);

    @Query("SELECT * FROM photo Where photo.local = 0")
    List<PhotoFeedItem> getLocals();

}
