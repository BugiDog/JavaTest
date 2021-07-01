package com.example.TestServer16.repositories;

import com.example.TestServer16.models.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepo extends CrudRepository<Note, Integer> {

    Iterable<Note> findByUserToken(String token);


}
