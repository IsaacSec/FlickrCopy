package com.example.isaac.flickrcopy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GalleryActivity extends AppCompatActivity {

    private TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initGUIElements();
    }

    private void initGUIElements(){
        Intent intent = getIntent();
        String text = intent.getStringExtra("category");
        category = (TextView) findViewById(R.id.galleryCategory);
        category.setText(text);
    }
}
