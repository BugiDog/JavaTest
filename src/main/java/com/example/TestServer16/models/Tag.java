package com.example.TestServer16.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Integer id;

    private String userToken;
    private String name;
    private Boolean isActive;
    @ManyToMany(mappedBy = "tags")
    private Set<Note> notes = new HashSet<>();
//
//    public List<Note> getNoteArr() {
//        return noteArr;
//    }
//
//    public void setNoteArr(List<Note> noteArr) {
//        this.noteArr = noteArr;
//    }

    public Tag() {
    }

    public Tag(Tag tag, boolean active) {
        this.id= tag.getId();
        this.userToken = tag.getUserToken();
        this.name = tag.getName();
        this.isActive = active;
    }

    public Tag(String userToken, String name, Boolean isActive) {
        this.userToken = userToken;
        this.name = name;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        this.isActive = active;
    }

    public Set<Note> getNotes() {
        return notes;
    }
}
