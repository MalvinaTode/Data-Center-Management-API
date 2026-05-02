package org.example;
/* Comanda pt a crea o alarma si de o notifica serverului asociat*/
public class AddEventCommand implements Command {
    private final Database database; //receiverul comenzii

    //constructor
    public AddEventCommand(Database database) {
        this.database = database;
    }

    //metoda de executie a comenzii
    //primeste sub forma de stringuri parametrii + nr liniei din fisier
    @Override
    public void execute(String[] params, int lineNumber) {
        try { //extrag:
            //tipul alertei
            //severitatea
            //ip-ul serverului
            //mesajul alertei


            String typeStr = params[0].trim();
            String severityStr = params[1].trim();
            String ip = params[2].trim();
            String message = params[3].trim();

            //convertim valorile din string in enumuri
            AlertType type = AlertType.valueOf(typeStr.toUpperCase());
            Severity severity = Severity.valueOf(severityStr.toUpperCase());

            //cream alarma folosind factory
            Alert alert = AlertFactory.createAlert(type.name(), severity, message, ip);
            database.addAlert(alert); //salvez alerta in baza de date

            //caut serverul asociat adresei IP
            Server server = null;
            for (Server s : database.getServers()) {
                if (s.getIpAddress().equals(ip)) {
                    server = s;
                    break;
                }
            }

            //dacaa exista, notific observatorii
            if (server != null) {
                server.generateAlert(alert);
            }

            //msj conform cerintei
            System.out.println("ADD EVENT: " + ip + ": type = " + type +
                    " && severity = " + severity + " && message = " + message);

        } catch (Exception e) {
            System.out.println("Error processing ADD EVENT command");
        }
    }
}
