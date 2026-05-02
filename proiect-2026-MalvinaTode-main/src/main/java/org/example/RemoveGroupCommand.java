package org.example;
/*comanda pentru stergerea unui grup din baza de date*/

public class RemoveGroupCommand implements Command {
    private final Database database;

    public RemoveGroupCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(String[] params, int lineNumber) {
        try {
            // Verif param
            if (params.length == 0 || params[0].trim().isEmpty()) {
                throw new MissingIpAddressException("Server IP Address was not provided.");
            }
            
            String ip = params[0].trim(); //iau ipul

            //verific daca grupul exista dupa ip
            ResourceGroup found = null;
            for (ResourceGroup g : database.getResourceGroups()) {
                if (g.getIpAdress().equals(ip)) {
                    found = g;
                }
            }

            if (found != null) {
                database.getResourceGroups().remove(found); //daca l am gsit il sterg
                System.out.println("REMOVE GROUP: " + ip);
            } else {
                System.out.println("REMOVE GROUP: Group not found: ipAddress = " + ip);
            }
//tratez exceptile
        } catch (MissingIpAddressException e) {
            System.out.println("REMOVE GROUP: MissingIpAddressException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (Exception e) {
            System.out.println("Error processing REMOVE GROUP command");
        }
    }
}
