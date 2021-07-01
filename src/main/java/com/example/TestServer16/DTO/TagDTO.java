package com.example.TestServer16.DTO;


import com.example.TestServer16.models.Tag;

public class TagDTO {
    private Integer id;

    private String userToken;
    private String name;
    private Boolean isActive;

    public TagDTO() {

    }

    public TagDTO(Integer id, String userToken, String name, Boolean isActive) {
        this.id = id;
        this.userToken = userToken;
        this.name = name;
        this.isActive = isActive;
    }

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.userToken = tag.getUserToken();
        this.name = tag.getName();
        this.isActive = tag.getIsActive();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
