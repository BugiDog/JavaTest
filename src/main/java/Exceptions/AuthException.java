package Exceptions;

public class AuthException extends Exception{
    private String massage;

    public String getMassage() {
        return massage;
    }

    public AuthException(String massage) {
        this.massage = massage;
    }
}
