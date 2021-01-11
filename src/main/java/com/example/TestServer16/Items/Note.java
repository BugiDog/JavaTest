package com.example.TestServer16.Items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Integer id;

    private String userToken;
    private String title;
    private String description;
    private boolean isPinned;
//    private Tag [] tagArray;

    public Note() {
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

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public Note(String userToken, String title, String description, boolean isPinned) {
        this.userToken = userToken;
        this.title = title;
        this.description = description;
        this.isPinned = isPinned;
//        this.tagArray = tagArray;
    }
}
