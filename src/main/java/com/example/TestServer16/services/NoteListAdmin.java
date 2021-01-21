package com.example.TestServer16.services;

import com.example.TestServer16.DTO.NoteDTO;
import com.example.TestServer16.models.Note;
import com.example.TestServer16.models.Tag;
import com.example.TestServer16.repositories.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdmin {

    public List<NoteDTO> NoteListFilter (Iterable<Note> noteList,Iterable<Tag> tagList) {

        List<NoteDTO> target = new ArrayList<>();
        List<NoteDTO> finalNoteList = new ArrayList<>();

        for(Note item:noteList ){
            NoteDTO noteDTO = new NoteDTO(item,tagList);
            target.add(noteDTO);
            System.out.println("ID"+noteDTO.getId());
        }

        for(NoteDTO item:target ){
            if(item.getIsPinned()){
                finalNoteList.add(item);
            }
        }
        for(NoteDTO item:target ){
            if(!item.getIsPinned()){
                finalNoteList.add(item);
            }
        }

        return finalNoteList;
    }


}
