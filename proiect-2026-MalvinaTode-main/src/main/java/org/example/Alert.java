package org.example;

public class Alert {
    //campuri obligatorii
    private AlertType type;
    private Severity severity;
    private String message;
    private String ipAdress;

    //constructor
    public Alert(AlertType type, Severity severity, String message, String ipAdress) {
        this.type = type;
        this.severity = severity;
        this.message = message;
        this.ipAdress = ipAdress;
    }

    //gettere
    public AlertType getType() {
        return type;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public String getIpAdress() {
        return ipAdress;
    }

}