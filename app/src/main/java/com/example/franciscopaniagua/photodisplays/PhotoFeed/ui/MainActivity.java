package com.example.franciscopaniagua.photodisplays.PhotoFeed.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.franciscopaniagua.photodisplays.PhotoDetail.PhotoDetailActivity;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.PhotoFeedPresenter;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.adapter.PhotoClick;
import com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.adapter.PhotoFeedAdapter;
import com.example.franciscopaniagua.photodisplays.PhotoFeedApp;
import com.example.franciscopaniagua.photodisplays.R;
import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;
import com.example.franciscopaniagua.photodisplays.lib.Constants;
import com.example.franciscopaniagua.photodisplays.lib.Utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PhotoFeedView, PhotoClick, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.photoFeed)
    RecyclerView photoFeed;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.feedContainer)
    ConstraintLayout feedContainer;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    private PhotoFeedPresenter presenter;
    private PhotoFeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setInjection();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //.setAction("Action", null).show();
                dispatchTakePictureIntent();
            }
        });
        photoFeed.setLayoutManager(new LinearLayoutManager(this));
        photoFeed.setAdapter(adapter);
        presenter.getPhotos();
        refreshlayout.setOnRefreshListener(this);

    }

    private void setInjection() {
        presenter = ((PhotoFeedApp) getApplication()).getPhotoFeedComponent(this, this).getPhotoFeedPresenter();
        presenter.onCreate();
        adapter = ((PhotoFeedApp) getApplication()).getPhotoFeedComponent(this, this).getPhotoFeedAdapter();
        adapter.setClick(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(feedContainer, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(boolean show) {
        refreshlayout.setRefreshing(show);
    }

    @Override
    public void showMessage(int idString) {
        Snackbar.make(feedContainer, getApplicationContext().getText(idString), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showImages(List<PhotoFeedItem> items) {
        adapter.setItems(items);
    }

    @Override
    public void addImage(PhotoFeedItem item) {
        adapter.addItem(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                showMessage(R.string.generic_error);
                return;
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT){
                    photoURI = Uri.fromFile(photoFile);
                }else {
                    photoURI = FileProvider.getUriForFile(MainActivity.this,
                            "com.example.franciscopaniagua.fileprovider",
                            photoFile);
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            addPhotoFeedItem();
        }else{
            File file = new File(mCurrentPhotoPath);
            file.delete();
        }
    }

    private void addPhotoFeedItem() {

        PhotoFeedItem photoFeedItem = new PhotoFeedItem();
        photoFeedItem.setComment("");
        photoFeedItem.setId(Calendar.getInstance().getTimeInMillis());
        photoFeedItem.setLocal(true);
        photoFeedItem.setPhoto(mCurrentPhotoPath);
        try {
            photoFeedItem.setPublishedAt(Utils.formatDateToServer(Calendar.getInstance().getTimeInMillis()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        photoFeedItem.setTitle("photo_"+Calendar.getInstance().getTimeInMillis() + "");
        presenter.savePhoto(photoFeedItem);
        addImage(photoFeedItem);
    }

    @Override
    public void clickInPhoto(PhotoFeedItem item, ImageView iv) {
        Intent i = new Intent(this, PhotoDetailActivity.class);
        i.putExtra(Constants.PHOTO_ID,item.getId());
        i.putExtra(Constants.PHOTO_Comment,item.getComment());
        i.putExtra(Constants.PHOTO_Title,item.getTitle());
        i.putExtra(Constants.PHOTO_Date,item.getPublishedAt());
        i.putExtra(Constants.PHOTO_Url,item.getPhoto());
        i.putExtra(Constants.PHOTO_Local,item.isLocal());

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this,
                        iv,
                        ViewCompat.getTransitionName(iv));

        startActivity(i,options.toBundle());
    }

    @Override
    public void onRefresh() {
        presenter.getPhotos();
    }
}
