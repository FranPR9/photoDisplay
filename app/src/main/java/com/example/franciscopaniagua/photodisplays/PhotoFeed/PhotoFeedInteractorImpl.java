package com.example.franciscopaniagua.photodisplays.PhotoFeed;

import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

public class PhotoFeedInteractorImpl implements PhotoFeedInteractor{

    private PhotoFeedRepository repository;

    public PhotoFeedInteractorImpl(PhotoFeedRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getPhotos() {
        repository.getPhotos();
    }

    @Override
    public void savePhoto(PhotoFeedItem item) {
        repository.savePhotoToDB(item);
    }
}
