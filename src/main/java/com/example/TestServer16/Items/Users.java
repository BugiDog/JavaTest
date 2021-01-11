package com.example.TestServer16.Items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.SecureRandom;
import java.util.Base64;



@Entity
public class Users {

    @Id
    @GeneratedValue
    private Integer id;

    private String token=generateNewToken();

    public String getToken() {
        return token;
    }

    public Users() {
    }

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }


}
