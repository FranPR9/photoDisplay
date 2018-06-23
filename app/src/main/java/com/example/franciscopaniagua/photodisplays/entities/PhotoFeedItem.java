package com.example.franciscopaniagua.photodisplays.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "photo")
public class PhotoFeedItem {

    @SerializedName("title")
    @Getter
    @Setter
    private String title;
    @SerializedName("publishedAt")
    @Getter
    @Setter
    private String publishedAt;
    @SerializedName("photo")
    @Getter
    @Setter
    private String photo;
    @SerializedName("id")
    @Getter
    @Setter
    @PrimaryKey
    private Long id;
    @SerializedName("comment")
    @Getter
    @Setter
    private String comment;
    @Getter
    @Setter
    private boolean local = false;
}
