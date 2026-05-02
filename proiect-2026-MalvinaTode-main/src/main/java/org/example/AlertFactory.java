package org.example;
//am folosit factory pattern pentru a crea alerte de tipuri diferite
public class AlertFactory {

    public static Alert createAlert(String type,
                                    Severity severity,
                                    String message,
                                    String ipAddress) {

        switch (type) {
            case "ANOMALY":
                return new Alert(AlertType.ANOMALY, severity, message, ipAddress);

            case "ADVISORY":
                return new Alert(AlertType.ADVISORY, severity, message, ipAddress);

            default:
                return null;
        }
    }
}
