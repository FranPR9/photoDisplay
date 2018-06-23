package com.example.franciscopaniagua.photodisplays.PhotoFeed.ui;

import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

import java.util.List;

public interface PhotoFeedView {

    void showMessage(String message);
    void showLoading(boolean show);
    void showMessage(int idString);
    void showImages(List<PhotoFeedItem> items);
    void addImage(PhotoFeedItem item);

}
