package com.example.TestServer16.controlers;

import com.example.TestServer16.Items.Note;
import com.example.TestServer16.Items.Users;
import com.example.TestServer16.NoteListAdmin;
import com.example.TestServer16.repos.NoteRepo;
import com.example.TestServer16.repos.UsersRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RestControler {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private NoteRepo noteRepo;


    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public Users addUser(){
        System.out.println("addUsers--------------------------");
        Users newUsers = new Users();
        //usersRepo.save(newUsers);

        return  newUsers ;
    }

    @RequestMapping(value = "/loadNoteList", method = RequestMethod.GET)
    public List<Note> loadNoteList(@RequestParam(value = "token") String token){
        System.out.println("loadNoteList--------------------------");
        Iterable <Note>  noteList = noteRepo.findByUserToken(token);
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<Note> res = NoteAdmen.NoteListFilter(noteList);
        return res;
    }

    @RequestMapping(value = "/addNote", method = RequestMethod.POST)//убрать userToken из отправки на бэк
    public List<Note> addNote(@RequestBody String data){
        System.out.println("addNote--------------------------");
        System.out.println(data);
        Note newNote = null;
        try {
            newNote = new ObjectMapper().readValue(data, Note.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        noteRepo.save(newNote);
        Iterable <Note>  noteList = noteRepo.findByUserToken(newNote.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<Note> res = NoteAdmen.NoteListFilter(noteList);

        return res;
    }

    @RequestMapping(value = "/pinNote", method = RequestMethod.GET)
    public List<Note> pinNote(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "token") String token
    ){
        System.out.println("pinNote--------------------------");
        int noteId = Integer.parseInt(id);
       // Optional<Note> note = noteRepo.findById(noteId);
       // Optional<Note> note = Optional.of(noteRepo.findById(noteId)).get();
        Note note = noteRepo.findById(noteId).get();
        note.setPinned(!note.getIsPinned());
        noteRepo.save(note);
        Iterable <Note>  noteList = noteRepo.findByUserToken(token);
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<Note> res = NoteAdmen.NoteListFilter(noteList);
        return res;
    }



}
