package com.example.TestServer16.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.*;


@Entity
public class Note {
    @Id
    @GeneratedValue
    private Integer id;

    private String userToken;
    private String title;
    private String description;
    private boolean isPinned;
    private int[] activeTagsArray;
    //    @Column(length = 15000)
//    private String tagsArray;
//    @ElementCollection
//    private List<Tag> tagsArray;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "note_tag",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
//
//    public void addTag(Tag tag){
//        tagsArray.add(tag);
//        tag.getNoteArr().add(this);
//    }
//
//    public void removeTag(Tag tag){
//        tagsArray.remove(tag);
//        tag.getNoteArr().remove(this);
//    }

    public Note() {
    }

    public Note(String userToken, String title, String description, boolean isPinned, int[] activeTagsArray) {
        this.userToken = userToken;
        this.title = title;
        this.description = description;
        this.isPinned = isPinned;
        this.activeTagsArray = activeTagsArray;
    }

//    public Note(Map note) {
//        this.userToken = note.get("userToken").toString();
//        this.title = note.get("title").toString();
//        this.description = note.get("description").toString();
//        this.isPinned = (boolean) note.get("isPinned");
//        //this.activeTagsArray=activtagArrGener(note.get("activeTagsArray").toString());
//    }

    private int[] activtagArrGener(String str) {
        int[] rez = new int[0];
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Tag> participantJsonList = mapper.readValue(str, new TypeReference<List<Tag>>() {
            });
            for (Tag tag : participantJsonList) {
                if (tag.getIsActive()) {
                    rez = Arrays.copyOf(rez, rez.length + 1);
                    rez[rez.length - 1] = tag.getId();
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("rez" + rez.toString());
        return rez;
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

//    public int[] getActiveTagsArray() {
//        return activeTagsArray;
//    }

    public int[] getActiveTagsArray() {
        return activeTagsArray;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
