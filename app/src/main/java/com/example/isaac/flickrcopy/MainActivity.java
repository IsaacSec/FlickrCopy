package com.example.isaac.flickrcopy;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.CategoryAdapter;
import model.CategoryRow;

public class MainActivity extends AppCompatActivity {

    private ListView categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGUIElements();
    }

    private ArrayList<CategoryRow> getCategories() {
        ArrayList<CategoryRow> categories = new ArrayList<>();

        categories.add(new CategoryRow(this,"Books"));
        categories.add(new CategoryRow(this, "Cars"));
        categories.add(new CategoryRow(this, "Games"));
        categories.add(new CategoryRow(this, "Jewelry"));
        categories.add(new CategoryRow(this, "Landscape"));
        categories.add(new CategoryRow(this, "Love"));
        categories.add(new CategoryRow(this, "Movies"));
        categories.add(new CategoryRow(this, "Music"));
        categories.add(new CategoryRow(this, "Technology"));
        categories.add(new CategoryRow(this, "Travel"));

        return categories;
    }

    private void initGUIElements(){
        categories = (ListView) findViewById(R.id.lvCategories);

        ArrayAdapter<CategoryRow> adapter = new CategoryAdapter(this, getCategories());
        categories.setAdapter(adapter);

        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ConstraintLayout layout = (ConstraintLayout) view;
                TextView tv = layout.findViewById(R.id.categoryText);
                String selected = ""+tv.getText();

                Intent galleryActivity = new Intent(MainActivity.this, GalleryActivity.class);
                galleryActivity.putExtra("category", selected);
                System.out.println(selected);
                startActivity(galleryActivity);
            }
        });
    }
}
