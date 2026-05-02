package org.example;
/*cmd pt a adauga un grup in functie de adresa IP*/
public class AddGroupCommand implements Command {
    private final Database database;

    public AddGroupCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(String[] params, int lineNumber) {
        try {
            //verific daca exista parametri
            if (params.length == 0 || params[0].trim().isEmpty()) {
                throw new MissingIpAddressException("Server IP Address was not provided.");
            }
            
            // iau ip ul
            String ip = params[0].trim();

            ResourceGroup group = new ResourceGroup(ip);
            database.addResourceGroup(group);

            // gasesc severul cu acc ip si il contectez la grup
            for (Server server : database.getServers()) {
                if (server.getIpAddress().equals(ip)) {
                    server.attach(group);
                    break;
                }
            }

            System.out.println("ADD GROUP: " + ip);

        } catch (MissingIpAddressException e) {
            System.out.println("ADD GROUP: MissingIpAddressException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (Exception e) {
            System.out.println("Error processing ADD GROUP command");
        }
    }
}
