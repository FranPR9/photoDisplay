package com.example.franciscopaniagua.photodisplays.PhotoFeed;

import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

public interface PhotoFeedRepository {

    void getPhotos();
    void savePhotoToDB(final PhotoFeedItem item);
}
