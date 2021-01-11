package com.example.TestServer16.repos;

import com.example.TestServer16.Items.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepo extends CrudRepository<Note, Integer> {

    Iterable <Note>  findByUserToken(String token);



}
