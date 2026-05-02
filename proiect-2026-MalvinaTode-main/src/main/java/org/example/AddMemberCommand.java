package org.example;
/*  Comanda pt a adauga un  membru in grup

 */
public class AddMemberCommand implements Command {
    private final Database database;

    public AddMemberCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(String[] params, int lineNumber) {
        try {
            // params[0]=ip
            // params[1]=name
            // params[2]=role

            if (params.length == 0 || params[0].trim().isEmpty()) {
                throw new MissingIpAddressException("Server IP Address was not provided.");
            }

            String ip = params[0].trim(); //iau ipul

            //caut grupul dupa ip
            ResourceGroup group = null;
            for (ResourceGroup g : database.getResourceGroups()) {
                if (g.getIpAdress().equals(ip)) {
                    group = g;
                }
            }

            if (group == null) {
                System.out.println("ADD MEMBER: Group not found: ipAddress = " + ip);
                return;
            }

            // verific  pt name si rol
            if (params.length < 3 || params[1].trim().isEmpty() || params[2].trim().isEmpty()) {
                throw new UserException("Name and role can't be empty.");
            }

            String name = params[1].trim();
            String role = params[2].trim();

            //creez userul si il adaug in grup
            User user = new Operator(name, role, "", "");

            group.addMember(user);
            System.out.println("ADD MEMBER: " + ip + ": name = " + name + " && role = " + role);

        } catch (MissingIpAddressException e) {
            System.out.println("ADD MEMBER: MissingIpAddressException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (UserException e) {
            System.out.println("ADD MEMBER: UserException: " + e.getMessage() + " ## line no: " + lineNumber);
        } catch (Exception e) {
            System.out.println("Error processing ADD MEMBER command");
        }
    }
}
