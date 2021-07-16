package com.example.TestServer16.controllers;

import com.example.TestServer16.DTO.NoteAndTagDTO;
import com.example.TestServer16.DTO.NoteDTO;
import com.example.TestServer16.DTO.TagDTO;
import com.example.TestServer16.models.Note;
import com.example.TestServer16.models.Tag;
import com.example.TestServer16.models.User;
import com.example.TestServer16.services.NoteListAdmin;
import com.example.TestServer16.repositories.NoteRepo;
import com.example.TestServer16.repositories.TagRepo;
import com.example.TestServer16.repositories.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins ="*")
public class RestControler {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private TagRepo tagRepo;

    @CrossOrigin(origins ="*")
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public User addUser() {
        System.out.println("addUsers--------------------------");
        User newUsers = new User();
        userRepo.save(newUsers);
        return newUsers;
    }

    @RequestMapping(value = "/searchNote", method = RequestMethod.GET)
    public List<NoteDTO> searchNote(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "searchQuery") String searchQuery) {
        System.out.println("searchNote--------------------------");
        Iterable<Tag> tagList = tagRepo.findByUserToken(token);
        Iterable<Note> noteList = NoteGener(tagList, token);
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> res = NoteAdmen.NoteListFilter(noteList, tagList, searchQuery);
        return res;
    }

    @RequestMapping(value = "/loadNoteList", method = RequestMethod.GET)
    public List<NoteDTO> loadNoteList(@RequestParam(value = "token") String token) {
        System.out.println("loadNoteList--------------------------");
        //Iterable <Note>  noteList = noteRepo.findByUserToken(token);
        Iterable<Tag> tagList = tagRepo.findByUserToken(token);
        Iterable<Note> noteList = NoteGener(tagList, token);
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        String searchQuery = "";
        List<NoteDTO> res = NoteAdmen.NoteListFilter(noteList, tagList, searchQuery);
        return res;
    }

    @RequestMapping(value = "/addNote", method = RequestMethod.POST)//убрать userToken из отправки на бэк
    public List<NoteDTO> addNote(
            @RequestBody String data,
            @RequestParam(value = "searchQuery") String searchQuery) {
        System.out.println("addNote--------------------------");
        System.out.println(data);
        Note newNote = null;
        try {

            ObjectMapper mapper = new ObjectMapper();
            newNote = mapper.readValue(data, Note.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (int item : newNote.getActiveTagsArray()) {
            Tag tag = tagRepo.findById(item).get();
            newNote.getTags().add(tag);
            tag.getNotes().add(newNote);
        }
        noteRepo.save(newNote);
        Iterable<Tag> tagList = tagRepo.findByUserToken(newNote.getUserToken());
        Iterable<Note> noteList = NoteGener(tagList, newNote.getUserToken());


        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> res = NoteAdmen.NoteListFilter(noteList, tagList, searchQuery);

        return res;
    }

    @RequestMapping(value = "/pinNote", method = RequestMethod.GET)
    public List<NoteDTO> pinNote(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "searchQuery") String searchQuery
    ) {
        System.out.println("pinNote--------------------------");
        int noteId = Integer.parseInt(id);
        Note newNote = noteRepo.findById(noteId).get();
        newNote.setPinned(!newNote.getIsPinned());
        noteRepo.save(newNote);
        //Iterable <Note>  noteList = noteRepo.findByUserToken(note.getUserToken());
        Iterable<Tag> tagList = tagRepo.findByUserToken(newNote.getUserToken());
        Iterable<Note> noteList = NoteGener(tagList, newNote.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> res = NoteAdmen.NoteListFilter(noteList, tagList, searchQuery);
        return res;
    }

    @RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
    public List<NoteDTO> deleteNote(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "searchQuery") String searchQuery
    ) {
        System.out.println("deleteNote--------------------------");
        int noteId = Integer.parseInt(id);
        Note newNote = noteRepo.findById(noteId).get();
        noteRepo.deleteById(noteId);
        //Iterable <Note>  noteList = noteRepo.findByUserToken(note.getUserToken());
        Iterable<Tag> tagList = tagRepo.findByUserToken(newNote.getUserToken());
        Iterable<Note> noteList = NoteGener(tagList, newNote.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> res = NoteAdmen.NoteListFilter(noteList, tagList, searchQuery);
        return res;
    }

    @RequestMapping(value = "/editNote", method = RequestMethod.POST)
    public List<NoteDTO> editNote(
            @RequestBody String data,
            @RequestParam(value = "searchQuery") String searchQuery) {
        System.out.println("editNote--------------------------");
        System.out.println(data);
        Note newNote = null;
        try {
            newNote = new ObjectMapper().readValue(data, Note.class);
            System.out.println(newNote.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//       newNote.getTags().clear();
        for (int item : newNote.getActiveTagsArray()) {
            Tag tag = tagRepo.findById(item).get();
            newNote.getTags().add(tag);
            tag.getNotes().add(newNote);
        }
        noteRepo.save(newNote);
        //Iterable <Note>  noteList = noteRepo.findByUserToken(newNote.getUserToken());
        Iterable<Tag> tagList = tagRepo.findByUserToken(newNote.getUserToken());

        Iterable<Note> noteList = NoteGener(tagList, newNote.getUserToken());

        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> res = NoteAdmen.NoteListFilter(noteList, tagList, searchQuery);

        return res;
    }

    @RequestMapping(value = "/addTag", method = RequestMethod.POST)
    public NoteAndTagDTO addTag(
            @RequestBody String data,
            @RequestParam(value = "searchQuery") String searchQuery) {
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
        Iterable<Tag> tagsList = tagRepo.findByUserToken(newTag.getUserToken());
        List<TagDTO> rezTagsList = new ArrayList<>();
        for (Tag tag : tagsList) {
            TagDTO tagDTO = new TagDTO(tag);
            rezTagsList.add(tagDTO);
        }
        Iterable<Note> noteList = NoteGener(tagsList, newTag.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> NoteList = NoteAdmen.NoteListFilter(noteList, tagsList, searchQuery);
        NoteAndTagDTO rez = new NoteAndTagDTO(NoteList, rezTagsList);
        return rez;
    }

    @RequestMapping(value = "/deleteTag", method = RequestMethod.GET)
    public NoteAndTagDTO deleteTag(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "searchQuery") String searchQuery
    ) {
        System.out.println("deleteTag--------------------------");
        int tagId = Integer.parseInt(id);
        Tag newTag = tagRepo.findById(tagId).get();
        for (Note note : newTag.getNotes()) {
            note.getTags().remove(newTag);
        }

        tagRepo.deleteById(tagId);
        Iterable<Tag> tagsList = tagRepo.findByUserToken(newTag.getUserToken());
        List<TagDTO> rezTagsList = new ArrayList<>();
        for (Tag tag : tagsList) {
            TagDTO tagDTO = new TagDTO(tag);
            rezTagsList.add(tagDTO);
        }
        Iterable<Note> noteList = NoteGener(tagsList, newTag.getUserToken());
        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> NoteList = NoteAdmen.NoteListFilter(noteList, tagsList, searchQuery);
        NoteAndTagDTO rez = new NoteAndTagDTO(NoteList, rezTagsList);
        return rez;
    }

    @RequestMapping(value = "/loadTagList", method = RequestMethod.GET)
    public List<TagDTO> loadTagList(
            @RequestParam(value = "token") String token
    ) {
        System.out.println("loadTagList--------------------------");
        Iterable<Tag> tagsList = tagRepo.findByUserToken(token);
        List<TagDTO> rezTagsList = new ArrayList<>();
        for (Tag tag : tagsList) {
            TagDTO tagDTO = new TagDTO(tag);
            rezTagsList.add(tagDTO);
        }

        return rezTagsList;
    }

    @RequestMapping(value = "/changeTagStatus", method = RequestMethod.GET)
    public NoteAndTagDTO changeTagStatus(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "searchQuery") String searchQuery
    ) {
        System.out.println("changeTagStatus--------------------------");
        int tagId = Integer.parseInt(id);
        Tag newTag = tagRepo.findById(tagId).get();
        newTag.setIsActive(!newTag.getIsActive());
        tagRepo.save(newTag);
        Iterable<Tag> tagList = tagRepo.findByUserToken(newTag.getUserToken());

        Iterable<Note> noteList = NoteGener(tagList, newTag.getUserToken());

        NoteListAdmin NoteAdmen = new NoteListAdmin();
        List<NoteDTO> NoteList = NoteAdmen.NoteListFilter(noteList, tagList, searchQuery);
        List<TagDTO> rezTagsList = new ArrayList<>();
        for (Tag tag : tagList) {
            TagDTO tagDTO = new TagDTO(tag);
            rezTagsList.add(tagDTO);
        }
        NoteAndTagDTO rez = new NoteAndTagDTO(NoteList, rezTagsList);


//        Iterable <Note>  noteList = noteRepo.findByUserToken(tag.getUserToken());
//        NoteListAdmin NoteAdmen = new NoteListAdmin();
//        List<Note> res = NoteAdmen.NoteListFilter(noteList);
        return rez;
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

    private Iterable<Note> NoteGener(Iterable<Tag> tagList, String token) {
        Set<Note> noteSet = new HashSet<>();
        Iterable<Note> primaryNoteList = noteRepo.findByUserToken(token);
        for (Note note : primaryNoteList) {
            if (note.getIsPinned()) {
                noteSet.add(note);
            }
        }
        boolean flag = false;
        for (Tag tag : tagList) {
            if (tag.getIsActive()) {
                flag = true;
                for (Note note : tag.getNotes()) {
                    noteSet.add(note);
                }
            }
        }
        Iterable<Note> noteList;
        if (!flag) {
            noteList = primaryNoteList;
        } else {
            noteList = noteSet;
        }
        return noteList;
    }


}
