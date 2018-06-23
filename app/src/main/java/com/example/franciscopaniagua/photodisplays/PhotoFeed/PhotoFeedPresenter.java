package com.example.franciscopaniagua.photodisplays.PhotoFeed;

import com.example.franciscopaniagua.photodisplays.PhotoFeed.event.PhotoFeedEvent;
import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

public interface PhotoFeedPresenter {

    void getPhotos();
    void savePhoto(PhotoFeedItem item);
    void onCreate();
    void onDestroy();
    void onPhotoFeed(PhotoFeedEvent event);
}
