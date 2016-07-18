package gh25.raul.raulghweek3.pojo;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Raul on 09/05/2016.
 */
public class Mascota {

    private String name;
    private int likes = 0;
    private String photoID; // url
    private String id;


    public Mascota() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
