package com.example.TestServer16.DTO;

import java.util.List;

public class NoteAndTagDTO {
    private List<NoteDTO> NoteList;
    private List<TagDTO> TagList;

    public NoteAndTagDTO() {

    }

    public NoteAndTagDTO(List<NoteDTO> noteList, List<TagDTO> tagList) {
        NoteList = noteList;
        TagList = tagList;
    }

    public List<NoteDTO> getNoteList() {
        return NoteList;
    }

    public void setNoteList(List<NoteDTO> noteList) {
        NoteList = noteList;
    }

    public List<TagDTO> getTagList() {
        return TagList;
    }

    public void setTagList(List<TagDTO> tagList) {
        TagList = tagList;
    }
}
