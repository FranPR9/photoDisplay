package com.example.franciscopaniagua.photodisplays.PhotoFeed.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.franciscopaniagua.photodisplays.R;
import com.example.franciscopaniagua.photodisplays.entities.PhotoFeedItem;
import com.example.franciscopaniagua.photodisplays.lib.Constants;
import com.example.franciscopaniagua.photodisplays.lib.ImageLoader;
import com.example.franciscopaniagua.photodisplays.lib.Utils;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoFeedAdapter extends RecyclerView.Adapter<PhotoFeedAdapter.ViewHolder> {

    private List<PhotoFeedItem> items;
    private ImageLoader imageLoader;
    private PhotoClick click;

    public PhotoFeedAdapter(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public void setItems(List<PhotoFeedItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setClick(PhotoClick click) {
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_feed_cell, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final PhotoFeedItem item = items.get(position);
        holder.title.setText(item.getTitle());
        imageLoader.load(holder.ivPhoto, item.getPhoto());
        try {
            holder.date.setText(Utils.formatDate(item.getPublishedAt()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.photoFeedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.clickInPhoto(item,holder.ivPhoto);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        else
            return items.size();
    }

    public void addItem(PhotoFeedItem item) {
        if (item == null) return;
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.photoFeedCard)
        CardView photoFeedCard;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
