package com.example.TestServer16.controlers;

import com.example.TestServer16.Items.Users;
import com.example.TestServer16.repos.PersonRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RestControler {

    @Autowired
    private PersonRepo personRepo;


    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Iterable<Users> userList(){
        System.out.println("userList--------------------------");
        Iterable<Users>  personIterable = personRepo.findAll();
        return personIterable;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Users addUser(@RequestBody String data){
        System.out.println("addUser--------------------------");
        Users ronaldo = null;
        try {
            ronaldo= new ObjectMapper().readValue(data, Users.class);
            personRepo.save(ronaldo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ronaldo;
    }

}
