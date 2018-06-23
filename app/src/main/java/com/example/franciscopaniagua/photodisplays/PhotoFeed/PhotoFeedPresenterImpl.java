package com.example.franciscopaniagua.photodisplays.PhotoFeed;

import com.example.franciscopaniagua.photodisplays.PhotoFeed.event.PhotoFeedEvent;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.PhotoFeedView;
import com.example.franciscopaniagua.photodisplays.R;
import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;
import com.example.franciscopaniagua.photodisplays.lib.EventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PhotoFeedPresenterImpl implements PhotoFeedPresenter {

    private PhotoFeedInteractor interactor;
    private PhotoFeedView view;
    private EventBus eventBus;

    public PhotoFeedPresenterImpl(PhotoFeedInteractor interactor, PhotoFeedView view, EventBus eventBus) {
        this.interactor = interactor;
        this.view = view;
        this.eventBus = eventBus;
    }

    @Override
    public void getPhotos() {
        view.showLoading(true);interactor.getPhotos();
    }

    @Override
    public void savePhoto(PhotoFeedItem item) {
        interactor.savePhoto(item);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhotoFeed(PhotoFeedEvent event) {
        view.showLoading(false);
        if(event.isSuccess())
            switch (event.getType()){
                case PhotoFeedEvent.PHOTO_TYPE:
                    view.showImages(event.getPhotoItems());
                    break;
            }
         else
             view.showMessage(R.string.generic_error);
             ;
    }
}
