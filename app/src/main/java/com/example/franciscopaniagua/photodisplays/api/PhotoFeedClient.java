package com.example.franciscopaniagua.photodisplays.api;

import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class PhotoFeedClient {

    private Retrofit retrofit;
    //private final static String BASE_URL = "https://neulionmdnyc-a.akamaihd.net/fs/nba/feeds_s2012/stats/2016/boxscore/";

    public PhotoFeedClient() {
        Calendar c = Calendar.getInstance();
        String BASE_URL = "https://photomaton.herokuapp.com/api/";
        Log.d("PhotoFeedClient",BASE_URL);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public PhotoFeedService getPhotoFeedService() {
        return retrofit.create(PhotoFeedService.class);
    }

}
