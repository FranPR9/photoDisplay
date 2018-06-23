package com.example.franciscopaniagua.photodisplays.lib;

import android.widget.ImageView;

/**
 * Created by franpr9.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void loadDontAnimate(ImageView imageView, String URL);

    void setOnFinishedImageLoadingListener(Object object);

}
