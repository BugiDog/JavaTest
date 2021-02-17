package Entitys;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.Base64;

@NamedQueries({
        @NamedQuery(
                name = "findByLogin",
                query = "select login  from User where login = :login"
        )}
)

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String login;

    private String password;

    private String authToken;

    public User() {
    }
    public User(String name, String lastName) {
        this.login = name;
        this.password = lastName;
        authToken =generateNewToken();
    }
//    public User(Integer id,String name, String lastName) {
//        this.id=id;
//        this.login = name;
//        this.password = lastName;
//        authToken =generateNewToken();
//    }

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

    @Override
    public String toString() {
        return login+password;
    }

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
