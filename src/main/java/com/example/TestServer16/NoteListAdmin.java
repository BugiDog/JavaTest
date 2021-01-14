package com.example.TestServer16;

import com.example.TestServer16.Items.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdmin {

    public List<Note> NoteListFilter (Iterable<Note> noteList) {
        List<Note> target = new ArrayList<Note>();
        List<Note> finalNoteList = new ArrayList<Note>();
        noteList.forEach(target::add);

        for(Note item:target ){
            if(item.getIsPinned()){
                finalNoteList.add(item);
            }
        }
        for(Note item:target ){
            if(!item.getIsPinned()){
                finalNoteList.add(item);
            }
        }

        return finalNoteList;
    }


}
