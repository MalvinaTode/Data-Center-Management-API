package org.example;

import java.util.ArrayList;
import java.util.List;

public class ResourceGroup implements Observer {

    // atributele specifice grupului
    private List<User> members;
    private String ipAddress;

    // constructor
    public ResourceGroup(String ipAddress) {
        this.ipAddress = ipAddress;
        this.members = new ArrayList<>();
    }

    // getter pentru ip
    public String getIpAdress() {
        return ipAddress;
    }

    // adauga un membru in grup
    public void addMember(User user) {
        this.members.add(user);
    }

    // extrage lista de membri ai grupului
    public List<User> getMembers() {
        return this.members;
    }

    // elimina un membru din grup
    public void removeMember(User user) {
        this.members.remove(user);
    }

    // metoda update din interfata Observer
    @Override
    public void update(Alert alert) {
    }
}
