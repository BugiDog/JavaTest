package com.example.TestServer16.repositories;

import com.example.TestServer16.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {



}
