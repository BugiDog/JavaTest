package com.example.TestServer16.repos;

import com.example.TestServer16.Items.Users;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Users, Integer> {

}
