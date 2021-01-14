package com.example.TestServer16.controlers;

import com.example.TestServer16.Items.Note;
import com.example.TestServer16.Items.Tag;
import com.example.TestServer16.Items.Users;
import com.example.TestServer16.NoteListAdmin;
import com.example.TestServer16.repos.NoteRepo;
import com.example.TestServer16.repos.TagRepo;
import com.example.TestServer16.repos.UsersRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
    @Autowired
    private TagRepo tagRepo;


    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public Users addUser(){
        System.out.println("addUsers--------------------------");
        Users newUsers = new Users();
        usersRepo.save(newUsers);
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
            @RequestParam(value = "id") String id
    ){
        System.out.println("pinNote--------------------------");
        int noteId = Integer.parseInt(id);
        Note note = noteRepo.findById(noteId).get();
        note.setPinned(!note.getIsPinned());
        noteRepo.save(note);
        Iterable <Note>  noteList = noteRepo.findByUserToken(note.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<Note> res = NoteAdmen.NoteListFilter(noteList);
        return res;
    }

    @RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
    public List<Note> deleteNote(
            @RequestParam(value = "id") String id
    ){
        System.out.println("deleteNote--------------------------");
        int noteId = Integer.parseInt(id);
        Note note = noteRepo.findById(noteId).get();
        noteRepo.deleteById(noteId);
        Iterable <Note>  noteList = noteRepo.findByUserToken(note.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<Note> res = NoteAdmen.NoteListFilter(noteList);
        return  res ;
    }

    @RequestMapping(value = "/editNote", method = RequestMethod.POST)
    public List<Note> editNote(@RequestBody String data){
        System.out.println("editNote--------------------------");
        System.out.println(data);
        Note newNote = null;
        try {
            newNote = new ObjectMapper().readValue(data, Note.class);
            System.out.println(newNote.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        noteRepo.save(newNote);
        Iterable <Note>  noteList = noteRepo.findByUserToken(newNote.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<Note> res = NoteAdmen.NoteListFilter(noteList);

        return res;
    }

    @RequestMapping(value = "/addTag", method = RequestMethod.POST)
    public Iterable <Tag> addTag(@RequestBody String data){
        System.out.println("addTag--------------------------");
        System.out.println(data);
        Tag newTag = null;
        try {
            newTag = new ObjectMapper().readValue(data, Tag.class);
            System.out.println(newTag.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        tagRepo.save(newTag);
        Iterable <Tag>  tagsList = tagRepo.findByUserToken(newTag.getUserToken());

        return tagsList;
    }

    @RequestMapping(value = "/deleteTag", method = RequestMethod.GET)
    public Iterable <Tag> deleteTag(
            @RequestParam(value = "id") String id
    ){
        System.out.println("deleteTag--------------------------");
        int tagId = Integer.parseInt(id);
        Tag tag = tagRepo.findById(tagId).get();
        tagRepo.deleteById(tagId);
        Iterable <Tag>  tagList = tagRepo.findByUserToken(tag.getUserToken());

        return  tagList ;
    }

    @RequestMapping(value = "/loadTagList", method = RequestMethod.GET)
    public Iterable <Tag> loadTagList(
            @RequestParam(value = "token") String token
    ){
        System.out.println("loadTagList--------------------------");
        Iterable <Tag>  tagList = tagRepo.findByUserToken(token);

        return  tagList ;
    }

        @RequestMapping(value = "/changeTagStatus", method = RequestMethod.GET)
    public  Iterable <Tag> changeTagStatus(
            @RequestParam(value = "id") String id
    ){
        System.out.println("changeTagStatus--------------------------");
        int tagId = Integer.parseInt(id);
        Tag tag = tagRepo.findById(tagId).get();
        tag.setActive(!tag.getIsActive());
        tagRepo.save(tag);
        Iterable <Tag>  tagsList = tagRepo.findByUserToken(tag.getUserToken());
        Iterable <Note>  noteList = noteRepo.findByUserToken(tag.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<Note> res = NoteAdmen.NoteListFilter(noteList);
        return  tagsList ;
    }

//    @RequestMapping(value = "/changeTagStatus", method = RequestMethod.GET)
//    public  Iterable <Tag> changeTagStatus(
//            @RequestParam(value = "id") String id
//    ){
//        System.out.println("changeTagStatus--------------------------");
//        int tagId = Integer.parseInt(id);
//        Tag tag = tagRepo.findById(tagId).get();
//        tagRepo.setIsActive(tagId,!tag.getIsActive());
//        Iterable <Tag>  tagsList = tagRepo.findByUserToken(tag.getUserToken());
//        Iterable <Note>  noteList = noteRepo.findByUserToken(tag.getUserToken());
//        NoteListAdmin NoteAdmen = new NoteListAdmin();
//        List<Note> res = NoteAdmen.NoteListFilter(noteList);
//        return  tagsList ;
//    }



}
