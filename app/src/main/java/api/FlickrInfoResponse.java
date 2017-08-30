package api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by isaac on 8/30/17.
 */

public class FlickrInfoResponse {
    private String title;
    private String author;
    private String date;

    public FlickrInfoResponse (String response) throws JSONException {
        System.out.println("Info res: "+response);
        JSONObject json = new JSONObject(response);
        json = json.getJSONObject("photo");
        title = (json.getJSONObject("title")).getString("_content");
        author = (json.getJSONObject("owner")).getString("username");
        date = (json.getJSONObject("dates")).getString("taken");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
