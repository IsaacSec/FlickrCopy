package model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.isaac.flickrcopy.MainActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by isaac on 8/29/17.
 */

public class CategoryRow {
    private Drawable image;
    private String categoryTag;

    public CategoryRow(Context context, String categoryTag) {
        try {
            InputStream is = context.getAssets().open(categoryTag + ".png");
            Drawable d = Drawable.createFromStream(is, null);
            this.image = d;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.categoryTag = categoryTag;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
    }
}
