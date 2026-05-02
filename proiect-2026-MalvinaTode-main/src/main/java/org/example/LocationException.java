package org.example;

public class LocationException extends Exception {
    //am construit exceptiile conform labului7

    //costructor fara parametrii,dar cu mesaj
    public LocationException() {
        super("Location Exception: Country is missing.");
    }

    //constructo cu msj
    public LocationException(String message) {
        super(message);
    }
}
