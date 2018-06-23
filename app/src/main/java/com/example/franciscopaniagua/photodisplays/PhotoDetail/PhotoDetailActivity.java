package com.example.franciscopaniagua.photodisplays.PhotoDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.franciscopaniagua.photodisplays.R;
import com.example.franciscopaniagua.photodisplays.lib.Constants;
import com.example.franciscopaniagua.photodisplays.lib.GlideImageLoader;
import com.example.franciscopaniagua.photodisplays.lib.Utils;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.photoFeedCard)
    CardView photoFeedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        //GlideImageLoader imageLoader = new GlideImageLoader();
        //imageLoader.setLoaderContext(this);
        supportPostponeEnterTransition();

        Glide.with(this)
                .load(getIntent().getStringExtra(Constants.PHOTO_Url))
                .centerCrop()
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }
                })
                .into(ivPhoto);

        getIntent().getIntExtra(Constants.PHOTO_ID,0);
        comment.setText(getIntent().getStringExtra(Constants.PHOTO_Comment));
        title.setText(getIntent().getStringExtra(Constants.PHOTO_Title));
        try {
            date.setText(Utils.formatDate(getIntent().getStringExtra(Constants.PHOTO_Date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        getIntent().getBooleanExtra(Constants.PHOTO_Local,false);



    }
}
