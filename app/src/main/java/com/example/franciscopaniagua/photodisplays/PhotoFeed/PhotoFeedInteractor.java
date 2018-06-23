package com.example.franciscopaniagua.photodisplays.PhotoFeed;

import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

public interface PhotoFeedInteractor {

    void getPhotos();
    void savePhoto(PhotoFeedItem item);
}
