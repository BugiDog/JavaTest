package com.example.TestServer16.DTO;

import com.example.TestServer16.models.Note;
import com.example.TestServer16.models.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteDTO {
    private Integer id;

    private String userToken;
    private String title;
    private String description;
    private boolean isPinned;
    private List<Tag> tagsArray;

    public NoteDTO() {

    }

    public NoteDTO(Note note, Iterable<Tag> tagList) {
        this.id = note.getId();
        this.userToken = note.getUserToken();
        this.title = note.getTitle();
        this.description = note.getDescription();
        this.isPinned = note.getIsPinned();
        this.tagsArray = tagsListGener(note.getActiveTagsArray(), tagList);
    }

    public List<Tag> tagsListGener(int[] arr, Iterable<Tag> tagList) {
        for (int item : arr) {
            System.out.println(item);

        }
        System.out.println("-----------------------");

        List<Tag> rez = new ArrayList<>();
        for (Tag tag : tagList) {
            boolean flag = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == tag.getId()) {
                    Tag newTag = new Tag(tag, true);
                    rez.add(newTag);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Tag newTag = new Tag(tag, false);
                rez.add(newTag);
            }
        }

        return rez;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(boolean pinned) {
        isPinned = pinned;
    }

    public List<Tag> getTagsArray() {
        return tagsArray;
    }

    public void setTagsArray(List<Tag> tagsArray) {
        this.tagsArray = tagsArray;
    }

    @Override
    public String toString() {
        return title + description;
    }
}
