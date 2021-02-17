package DTO;

import javax.persistence.Column;

public class UserDTO {
    private Integer id;
    private String login;
    private String password;
    private String authToken;

    public UserDTO(Integer id, String login, String password, String authToken) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authToken = authToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
