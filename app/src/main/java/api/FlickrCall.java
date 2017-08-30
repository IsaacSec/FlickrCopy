package api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by isaac on 8/29/17.
 */

public class FlickrCall {
    public String API_KEY = "11721be0eb40de45e37027ac4530e3b9";
    public String API_URL = "https://api.flickr.com/services/rest/?format=json&method=";

    public JSONObject getPhotoSearchResponse(String tag){
        HttpClient client = new HttpClient();
        String url = API_URL+Method.PHOTOS_SEARCH+"&api_key="+API_KEY+"&tags=%22"+tag+"%22&nojsoncallback=1";
        System.out.println("URL:"+url);
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String response = client.run(url);
            System.out.println("Response: "+response);
            JSONObject json = new JSONObject(response);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException jsone){
            jsone.printStackTrace();
        }
        return null;
    }

    public ArrayList<FlickrPhotoResponse> getPhotos(String tag) throws JSONException{
        ArrayList<FlickrPhotoResponse> photos = new ArrayList<>();
        JSONObject json = getPhotoSearchResponse(tag);
        json = json.getJSONObject("photos");
        JSONArray photosJSON = json.getJSONArray("photo");
        int length = photosJSON.length();

        if (length > 10 ) {
            length = 10;
        }

        for (int i = 0; i < length; i++) {
            JSONObject item = photosJSON.getJSONObject(i);
            FlickrPhotoResponse photo = new FlickrPhotoResponse(item);
            photos.add(photo);
            System.out.println("Photo: "+photo.getId()+" "+photo.getSecret()+" "+photo.getFarm()+" "+photo.getServer());
        }

        return photos;
    }

    public ArrayList<Bitmap> getPhotoImages(ArrayList<FlickrPhotoResponse> photos) {
        ArrayList<Bitmap> images = new ArrayList<>();

        for (int i = 0; i < photos.size(); i++) {
            FlickrPhotoResponse photo = photos.get(i);
            String url = getImageURL(photo);
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                HttpClient client = new HttpClient();
                byte[] response = client.runForMedia(url);
                Bitmap bmp = BitmapFactory.decodeByteArray(response, 0, response.length);
                images.add(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return images;
    }

    public String getImageURL (FlickrPhotoResponse p){
        String url = "http://farm"+p.getFarm()+".staticflickr.com/"+p.getServer()+"/"+p.getId()+"_"+p.getSecret()+"_s.jpg";
        return url;
    }
}
