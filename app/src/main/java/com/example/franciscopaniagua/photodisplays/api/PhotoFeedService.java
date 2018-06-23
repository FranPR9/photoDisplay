package com.example.franciscopaniagua.photodisplays.api;

import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoFeedService {

    @GET("photo")
    Call<List<PhotoFeedItem>> getPhotoFeedItem();
}
