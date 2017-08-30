package api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by isaac on 8/29/17.
 */

public class FlickrPhotoResponse {
    private String farm;
    private String server;
    private String id;
    private String secret;

    public FlickrPhotoResponse(String farm, String server, String id, String secret) {
        this.farm = farm;
        this.server = server;
        this.id = id;
        this.secret = secret;
    }

    public FlickrPhotoResponse(JSONObject photo) throws JSONException{
        farm = photo.getString("farm");
        server = photo.getString("server");
        id = photo.getString("id");
        secret = photo.getString("secret");
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getServer() {
        return server;
    }


    public void setServer(String server) {
        this.server = server;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
