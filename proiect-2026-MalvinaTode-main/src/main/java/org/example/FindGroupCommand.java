package org.example;
/* Comanda pentru gasirea unui grup dupa adresa IP
*/
public class FindGroupCommand implements Command {
    private final Database database;

    public FindGroupCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(String[] params, int lineNumber) {
        try {
            // Verif daca am parametri
            if (params.length == 0 || params[0].trim().isEmpty()) {
                throw new MissingIpAddressException("Server IP Address was not provided.");
            }
            
            String ip = params[0].trim(); //iau ipul

            //caut grupul dupa ip
            ResourceGroup found = null;
            for (ResourceGroup g : database.getResourceGroups()) {
                if (g.getIpAdress().equals(ip)) {
                    found = g;
                }
            }

            if (found != null) {
                System.out.println("FIND GROUP: " + ip);
            } else {
                System.out.println("FIND GROUP: Group not found: ipAddress = " + ip);
            }
  //tratez excqeptiile
        } catch (MissingIpAddressException e) {
            System.out.println("FIND GROUP: MissingIpAddressException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (Exception e) {
            System.out.println("Error processing FIND GROUP command");
        }
    }
}
