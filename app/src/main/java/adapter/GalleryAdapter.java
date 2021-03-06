package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by isaac on 8/30/17.
 */

public class GalleryAdapter extends BaseAdapter {

    private Context mContext;
    // references to our images
    private ArrayList<Bitmap> images;

    public GalleryAdapter(Context context, ArrayList<Bitmap> m) {
        mContext = context;
        images = m;
    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(parent.getWidth()/5*2, parent.getWidth()/5*2));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Bitmap bmp = images.get(position);
        imageView.setImageBitmap(bmp);
        //imageView.setScaleX(3.0f);
        //imageView.setScaleY(3.0f);
        return imageView;
    }


}