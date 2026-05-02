package org.example;

public class AddServerCommand implements Command {

    private final Database database;

    public AddServerCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(String[] params, int lineNumber) {
        try {
            // params[1] = ip_address
            // params[3] = country
            // params[8] = user_name
            // params[9] = user_role
            // params[2] = server_statu

            if (params.length < 4) {
                throw new Exception("Insufficient parameters");
            }

            String ip = params[1].trim();        // iau ip-ul
            String country = params[3].trim();   // iau country
            String name = "";
            String role = "";
            String statusStr = "";

            if (params.length > 8 && params[8] != null) {
                name = params[8].trim();
            }

            if (params.length > 9 && params[9] != null) {
                role = params[9].trim();
            }

            if (params.length > 2 && params[2] != null) {
                statusStr = params[2].trim();
            }

            // verif de exceptii
            if (ip.isEmpty()) {
                throw new MissingIpAddressException("Server IP Address was not provided.");
            }

            if (name.isEmpty() || role.isEmpty()) {
                throw new UserException("Name and role can't be empty.");
            }

            if (country.isEmpty()) {
                throw new LocationException("Country is missing.");
            }

            // construiesc Location
            Location location = new Location(country);

            // construiesc User
            User owner = new Operator(name, role, "", "");

            // construiesc Server
            Server.Builder builder = new Server.Builder(ip, location, owner);

            if (!statusStr.isEmpty()) {
                try {
                    builder.setStatus(ServerStatus.valueOf(statusStr.toUpperCase()));
                } catch (IllegalArgumentException e) {
                }
            }

            //adaug serverul in db
            Server server = builder.build();
            database.addServer(server);

            System.out.println("ADD SERVER: " + ip + ": " + server.getStatus());
//ma ocup de exceptii
        } catch (MissingIpAddressException e) {
            System.out.println(
                    "ADD SERVER: MissingIpAddressException: " + e.getMessage() +
                            " ## line no: " + lineNumber
            );
        } catch (UserException e) {
            System.out.println(
                    "ADD SERVER: UserException: " + e.getMessage() +
                            " ## line no: " + lineNumber
            );
        } catch (LocationException e) {
            System.out.println(
                    "ADD SERVER: LocationException: " + e.getMessage() +
                            " ## line no: " + lineNumber
            );
        } catch (Exception e) {
            System.out.println("Error processing ADD SERVER command");
        }
    }
}
