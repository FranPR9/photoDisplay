package com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

public interface PhotoClick {

    void clickInPhoto(PhotoFeedItem item, ImageView iv);
}
