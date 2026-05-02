package org.example;

//factory pentru crearea comenzilor
public class CommandFactory {
    private final Database database;

    public CommandFactory(Database database) {
        this.database = database;
    }

    public Command createCommand(String commandType) {
        switch (commandType) {
            case "ADD SERVER":
                return new AddServerCommand(database);
            case "ADD GROUP":
                return new AddGroupCommand(database);
            case "FIND GROUP":
                return new FindGroupCommand(database);
            case "REMOVE GROUP":
                return new RemoveGroupCommand(database);
            case "ADD MEMBER":
                return new AddMemberCommand(database);
            case "FIND MEMBER":
                return new FindMemberCommand(database);
            case "REMOVE MEMBER":
                return new RemoveMemberCommand(database);
            case "ADD EVENT":
                return new AddEventCommand(database);
            default:
                throw new IllegalArgumentException("Unknown command: " + commandType);
        }
    }
}
