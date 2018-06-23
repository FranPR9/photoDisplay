package com.example.franciscopaniagua.photodisplays.PhotoFeed.event;

import com.example.franciscopaniagua.photodisplays.entities.Event;
import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PhotoFeedEvent extends Event {

    public static final int PHOTO_TYPE = 1;

    @Getter
    @Setter
    private List<PhotoFeedItem> photoItems;

}
