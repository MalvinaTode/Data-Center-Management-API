package org.example;

public class UserException extends Exception {

    //constructor fara parametrii,dar cu mesaj
    public UserException() {
        super("UserException: Name and role can't be empty.");
    }

    //constructor cu msj
    public UserException(String message) {
        super(message);
    }
}