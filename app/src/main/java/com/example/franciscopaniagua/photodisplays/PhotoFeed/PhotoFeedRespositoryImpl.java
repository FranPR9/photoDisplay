package com.example.franciscopaniagua.photodisplays.PhotoFeed;

import android.os.AsyncTask;

import com.example.franciscopaniagua.photodisplays.PhotoFeed.event.PhotoFeedEvent;
import com.example.franciscopaniagua.photodisplays.api.PhotoFeedService;
import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;
import com.example.franciscopaniagua.photodisplays.lib.EventBus;
import com.example.franciscopaniagua.photodisplays.model.PhotoDao;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFeedRespositoryImpl implements PhotoFeedRepository {

    private PhotoDao photoDao;
    private EventBus eventBus;
    private PhotoFeedService service;

    public PhotoFeedRespositoryImpl(EventBus eventBus, PhotoFeedService service, PhotoDao photoDao) {
        this.eventBus = eventBus;
        this.service = service;
        this.photoDao = photoDao;
    }

    @Override
    public void getPhotos() {
        //load de base de datos.
        //request de server.
        //save a base de datos.
        final Thread r = new Thread() {
            public void run() {
                getLocalPhotos();
            }
        };
        r.start();
        callRequest();

    }


    private void getLocalPhotos() {
        List<PhotoFeedItem> items = photoDao.getAll();
        if(items.size()>0)post(PhotoFeedEvent.PHOTO_TYPE,null,items,true);
    }

    private void post(int type, String error, List<PhotoFeedItem> items, boolean success) {
        PhotoFeedEvent event = new PhotoFeedEvent();
        event.setError(error);
        event.setType(type);
        event.setSuccess(success);
        event.setPhotoItems(items);

        eventBus.post(event);
    }

    private void callRequest() {

        Call<List<PhotoFeedItem>> call = service.getPhotoFeedItem();
        call.enqueue(new Callback<List<PhotoFeedItem>>() {
            @Override
            public void onResponse(Call<List<PhotoFeedItem>> call, final Response<List<PhotoFeedItem>> response) {
                if(response.isSuccess()){
                    final Thread r = new Thread() {
                        public void run() {
                            saveToDB(response.body());
                        }
                    };
                    r.start();
                }
            }

            @Override
            public void onFailure(Call<List<PhotoFeedItem>> call, Throwable t) {

            }
        });
    }


    private void saveToDB(final List<PhotoFeedItem> items) {
        if(items==null)return;
        if(items.size()==0)return;

        if(photoDao.getLocals().size() < items.size())
        {
            photoDao.insertAll(items);
            post(PhotoFeedEvent.PHOTO_TYPE,null,items,true);
        }

    }

    @Override
    public void savePhotoToDB(final PhotoFeedItem item) {
        if(item==null)return;
        final Thread r = new Thread() {
            public void run() {
                photoDao.save(item);
            }
        };
        r.start();

    }
}
