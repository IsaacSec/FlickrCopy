package com.example.isaac.flickrcopy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import adapter.GalleryAdapter;
import api.FlickrCall;
import api.FlickrPhotoResponse;

public class GalleryActivity extends AppCompatActivity {

    private TextView category;
    private GridView gridView;
    private FlickrCall flickr;
    private ArrayList<FlickrPhotoResponse> photos;
    private ArrayList<Bitmap> images;

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
        photos = new ArrayList<>();

        try {
            photos = flickr.getPhotos(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        images = flickr.getPhotoImages(photos);
        //ImageView im = new ImageView();
        //im.setImageDrawable(Drawable.createFromStream(images.get(0), null));
        gridView.setAdapter(new GalleryAdapter(this, images));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent infoActivity = new Intent(GalleryActivity.this, InfoActivity.class);
                FlickrPhotoResponse photo = photos.get(i);

                infoActivity.putExtra("id",photo.getId());
                infoActivity.putExtra("secret",photo.getSecret());
                infoActivity.putExtra("server",photo.getServer());
                infoActivity.putExtra("farm",photo.getFarm());

                Bitmap b = images.get(i);
                int bytes = b.getByteCount();
//or we can calculate bytes this way. Use a different value than 4 if you don't use 32bit images.
//int bytes = b.getWidth()*b.getHeight()*4;

                ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
                b.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

                byte[] array = buffer.array();
                infoActivity.putExtra("image", array);

                startActivity(infoActivity);
            }
        });
    }

    private void initGUIElements(){
        Intent intent = getIntent();
        String text = intent.getStringExtra("category");
        category = (TextView) findViewById(R.id.galleryCategory);
        category.setText(text);

        gridView = (GridView) findViewById(R.id.galleryIMages);
    }
}
