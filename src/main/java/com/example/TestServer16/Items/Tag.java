package com.example.TestServer16.Items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Integer id;

    private String userToken;
    private String name;
    private Boolean isActive;

    public Tag() {
    }

    public Tag(String userToken, String name, Boolean isActive) {
        this.userToken = userToken;
        this.name = name;
        this.isActive = isActive;
    }
}
