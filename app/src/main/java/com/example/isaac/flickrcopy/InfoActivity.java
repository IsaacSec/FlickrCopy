package com.example.isaac.flickrcopy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import api.FlickrCall;
import api.FlickrInfoResponse;

public class InfoActivity extends AppCompatActivity {

    private ImageView image;
    private TextView title;
    private TextView author;
    private TextView date;

    FlickrCall flickr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initGUIElements();
        System.out.println("INFO");
    }

    private void initGUIElements() {
        image = (ImageView) findViewById(R.id.infoImage);
        title = (TextView) findViewById(R.id.infoTitleRes);
        author = (TextView) findViewById(R.id.infoAuthorRes);
        date = (TextView) findViewById(R.id.infoDateRes);

        flickr = new FlickrCall();

        Intent intent = getIntent();
        String farm = intent.getStringExtra("farm");
        String server = intent.getStringExtra("server");
        String id = intent.getStringExtra("id");
        String secret = intent.getStringExtra("secret");
        //byte[] img = intent.getByteArrayExtra("image");
        Bitmap bmp = flickr.getPhotoImage(farm, server, id, secret);
        image.setImageBitmap(bmp);

        String response = flickr.getInfoSearchResponse(id);
        FlickrInfoResponse info = null;
        try {
            info = new FlickrInfoResponse(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        title.setText(info.getTitle());
        author.setText(info.getAuthor());
        date.setText(info.getDate());
    }
}
