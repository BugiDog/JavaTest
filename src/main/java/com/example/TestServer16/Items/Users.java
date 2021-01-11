package com.example.TestServer16.Items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Optional;


@Entity
public class Users {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String status;

    public Users() {
    }

    public Users(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }


}
