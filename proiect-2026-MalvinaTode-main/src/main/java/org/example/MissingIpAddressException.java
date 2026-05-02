package org.example;

public class MissingIpAddressException extends Exception {

    //constructor fara parametrii,dar cu mesaj
    public MissingIpAddressException() {
        super("MissingIpAddress Exception: Server IP Address was not provided.");
    }

    //constructor cu msj
    public MissingIpAddressException(String message) {
        super(message);
    }
}