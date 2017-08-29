package com.example.isaac.flickrcopy;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        categories.add(new CategoryRow(this, "Travel"));

        return categories;
    }

    private void initGUIElements(){
        categories = (ListView) findViewById(R.id.lvCategories);

        ArrayAdapter<CategoryRow> adapter = new CategoryAdapter(this, getCategories());
        categories.setAdapter(adapter);
    }
}
