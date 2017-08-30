package com.example.isaac.flickrcopy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;

import adapter.GalleryAdapter;
import api.FlickrCall;
import api.FlickrPhotoResponse;

public class GalleryActivity extends AppCompatActivity {

    private TextView category;
    private GridView gridView;
    private FlickrCall flickr;
    private ArrayList<InputStream> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initGUIElements();
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent intent = getIntent();
        String text = intent.getStringExtra("category");
        flickr = new FlickrCall();
        ArrayList<FlickrPhotoResponse> photos = new ArrayList<>();

        try {
            photos = flickr.getPhotos(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Bitmap> images = flickr.getPhotoImages(photos);
        //ImageView im = new ImageView();
        //im.setImageDrawable(Drawable.createFromStream(images.get(0), null));
        gridView.setAdapter(new GalleryAdapter(this, images));
        //gridView.setAdapter(new ImageAdapter);
    }

    private void initGUIElements(){
        Intent intent = getIntent();
        String text = intent.getStringExtra("category");
        category = (TextView) findViewById(R.id.galleryCategory);
        category.setText(text);

        gridView = (GridView) findViewById(R.id.galleryIMages);
    }
}
