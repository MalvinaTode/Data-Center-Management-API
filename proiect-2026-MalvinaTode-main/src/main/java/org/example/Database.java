package org.example;
import java.util.HashSet;
import java.util.Set;

public class Database {
    //campurile obligatorii
    private static Database instance;
    private Set<Server> servers;
    private Set<ResourceGroup> resourceGroups;
    private Set<Alert> alerts;


    //constructor
    private Database() {
        this.servers = new HashSet<>();
        this.resourceGroups = new HashSet<>();
        this.alerts = new HashSet<>();
    }

    //metoda getIntance pt a acessa baza de date, luata din lab11->singleton
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //gettere
    public Set<Server> getServers() {
        return servers;
    }
    public Set<ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }
    public Set<Alert> getAlerts() {
        return alerts;
    }

    //metodele cerute

    //adauga un singur server
    public void addServer(Server server) {
        this.servers.add(server);
    }

    //adauga o colectie de servere
    public void addServers(Set<Server> servers) {
        this.servers.addAll(servers);
    }

    //adauga un singur grup de monitorizare
    public void addResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroups.add(resourceGroup);
}

  //adauga o colectie de grupuri de monitorizare
    public void addResourceGroups(Set<ResourceGroup> resourceGroups) {
        this.resourceGroups.addAll(resourceGroups);
    }

    //adauga un singur alert
    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    //adauga o colectie de alerte
    public void addAlerts(Set<Alert> alerts) {
        this.alerts.addAll(alerts);
    }
}
