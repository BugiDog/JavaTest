package com.example.TestServer16.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NoteId_TagId {
    @Id
    @GeneratedValue
    private Integer id;

    private int NoteId;
    private int TagId;

    public NoteId_TagId() {
    }

    public NoteId_TagId(int noteId, int tagId) {
        NoteId = noteId;
        TagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNoteId() {
        return NoteId;
    }

    public void setNoteId(int noteId) {
        NoteId = noteId;
    }

    public int getTagId() {
        return TagId;
    }

    public void setTagId(int tagId) {
        TagId = tagId;
    }
}
