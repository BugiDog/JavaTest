package com.example.TestServer16.Items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;
import java.util.List;


@Entity
public class Note {
    @Id
    @GeneratedValue
    private Integer id;

    private String userToken;
    private String title;
    private String description;
    private boolean isPinned;
    private int [] TagsArray;

    public Note() {
    }

    public Note(String userToken, String title, String description, boolean isPinned,int [] activeTagsArray) {
        this.userToken = userToken;
        this.title = title;
        this.description = description;
        this.isPinned = isPinned;
        this.TagsArray = activeTagsArray;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public boolean getIsPinned() {
        return isPinned;
    }

    public int []  getActiveTagsArray() {
        return TagsArray;
    }


    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }


}
