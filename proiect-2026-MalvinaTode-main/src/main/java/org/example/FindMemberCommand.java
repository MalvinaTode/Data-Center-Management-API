package org.example;
/*comanda pentru gasirea unui membru pe baza adresei IP a grupului, numelui si rolului membrului*/
public class FindMemberCommand implements Command {
    private final Database database;

    public FindMemberCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(String[] params, int lineNumber) {
        try {
            // Verif parametri
            if (params.length == 0 || params[0].trim().isEmpty()) {
                throw new MissingIpAddressException("Server IP Address was not provided.");
            }
            
            if (params.length < 3 || params[1].trim().isEmpty() || params[2].trim().isEmpty()) {
                throw new UserException("Name and role can't be empty.");
            }
            
            String ip = params[0].trim();
            String name = params[1].trim();
            String role = params[2].trim();

            ResourceGroup group = null;
            //verific daca grupul exista dupa ip
            for (ResourceGroup g : database.getResourceGroups()) {
                if (g.getIpAdress().equals(ip)) {
                    group = g;
                }
            }

            if (group == null) {
                System.out.println("FIND MEMBER: Group not found: ipAddress = " + ip);
                return;
            }

            //caut membrul in grup dupa nume si rol
            User found = null;
            for (User u : group.getMembers()) {
                if (u.getName().equals(name) && u.getRole().equals(role)) {
                    found = u;
                }
            }

            if (found != null) {
                System.out.println("FIND MEMBER: " + ip + ": name = " + name + " && role = " + role);
            } else {
                System.out.println("FIND MEMBER: Member not found: ipAddress = " + ip +
                        ": name = " + name + " && role = " + role);
            }
//tratez exceptile
        } catch (MissingIpAddressException e) {
            System.out.println("FIND MEMBER: MissingIpAddressException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (UserException e) {
            System.out.println("FIND MEMBER: UserException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (Exception e) {
            System.out.println("Error processing FIND MEMBER command");
        }
    }
}
