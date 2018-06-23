package com.example.franciscopaniagua.photodisplays.lib;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by franpr9.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;
    private RequestListener onFinishedImageLoadingListener;
    int width,height;

    public void setLoaderContext(Activity activity) {
        this.glideRequestManager = Glide.with(activity);
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

    }

    @Override
    public void load(ImageView imageView, String URL) {

        if(URL.contains("http"))
        {
            //Log.d("glideimagelaoder","WEB !!!");
            glideRequestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(android.R.drawable.stat_sys_download)
                    //.centerCrop()
                    //.skipMemoryCache(true)
                    .centerCrop()
                    .into(imageView);
        }
        else
        {
            //Log.d("glideimagelaoder","local!!!");
            glideRequestManager
                    .load(new File(URL))
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(android.R.drawable.stat_sys_download)
                    .centerCrop()
                    //.centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void loadDontAnimate(ImageView imageView, String URL) {

        glideRequestManager
                .load(URL)
                .centerCrop()
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                        return false;
                    }
                })
                .into(imageView);
    }


    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        try {
            this.onFinishedImageLoadingListener = (RequestListener) listener;
        } catch (ClassCastException e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }
    }


}
