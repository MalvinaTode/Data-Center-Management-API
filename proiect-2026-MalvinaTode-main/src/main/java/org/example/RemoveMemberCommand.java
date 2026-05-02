package org.example;
/*comanda pt eliminarea unui membru dintr-un grup
*/
public class RemoveMemberCommand implements Command {
    private final Database database;

    public RemoveMemberCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(String[] params, int lineNumber) {
        try {
            // Verif parametrii
            if (params.length == 0 || params[0].trim().isEmpty()) {
                throw new MissingIpAddressException("Server IP Address was not provided.");
            }
            
            if (params.length < 3 || params[1].trim().isEmpty() || params[2].trim().isEmpty()) {
                throw new UserException("Name and role can't be empty.");
            }

            //iau datele
            String ip = params[0].trim();
            String name = params[1].trim();
            String role = params[2].trim();

            //verific daca grupul exista dupa ip
            ResourceGroup group = null;
            for (ResourceGroup g : database.getResourceGroups()) {
                if (g.getIpAdress().equals(ip)) {
                    group = g;
                }
            }

            if (group == null) { //nu exista grupul
                System.out.println("REMOVE MEMBER: Group not found: ipAddress = " + ip);
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
                group.removeMember(found); //daca il gasesc il elimin
                System.out.println("REMOVE MEMBER: " + ip + ": name = " + name + " && role = " + role);
            } else {
                System.out.println("REMOVE MEMBER: Member not found: ipAddress = " + ip +
                        ": name = " + name + " && role = " + role);
            }

            //tratez exceptiile
        } catch (MissingIpAddressException e) {
            System.out.println("REMOVE MEMBER: MissingIpAddressException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (UserException e) {
            System.out.println("REMOVE MEMBER: UserException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (Exception e) {
            System.out.println("Error processing REMOVE MEMBER command");
        }
    }
}
