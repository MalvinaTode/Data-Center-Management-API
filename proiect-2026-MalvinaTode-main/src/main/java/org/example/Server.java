package org.example;

import org.example.Location;
import java.util.ArrayList;
import java.util.List;

/*Builder pentru clasa Server cu parametri obligatorii si optionali, SINTAXA FOLOSITA DIN LAB11 EXACT CA IN LAB11
*/
public class Server implements Subject {

    private List<Observer> observers = new ArrayList<>();
//arraylist pentru observeri


    // parametri obligatorii
    private String ipAddress;
    private Location location;
    private User owner;

    // parametri optionali
    private String hostname;
    private ServerStatus status;
    private Integer cpuCores;
    private Integer ramGb;
    private Integer storageGb;

    //constructor cu builder
    private Server(Builder builder) {
        this.ipAddress = builder.ipAddress;
        this.location = builder.location;
        this.owner = builder.owner;
        this.hostname = builder.hostname;
        this.status = builder.status;
        this.cpuCores = builder.cpuCores;
        this.ramGb = builder.ramGb;
        this.storageGb = builder.storageGb;
    }

    public static class Builder {
        // obligatorii
        private String ipAddress;
        private Location location;
        private User owner;

        // optionali
        private String hostname;
        private ServerStatus status;
        private Integer cpuCores;
        private Integer ramGb;
        private Integer storageGb;

        //constructor doar cu param obligatorii cu builder
        public Builder(String ipAddress, Location location, User owner) {
            this.ipAddress = ipAddress;
            this.location = location;
            this.owner = owner;
        }

        //metodele set cu builder pentru param optionali

        public Builder setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder setStatus(ServerStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCpuCores(Integer cpuCores) {
            this.cpuCores = cpuCores;
            return this;
        }

        public Builder setRamGb(Integer ramGb) {
            this.ramGb = ramGb;
            return this;
        }

        public Builder setStorageGb(Integer storageGb) {
            this.storageGb = storageGb;
            return this;
        }

        public Server build() {
            return new Server(this);
        }
    }


    //metode pt patternul observer
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Alert alert) {
        for (Observer observer : observers) {
            observer.update(alert);
        }
    }

    //met folosita la add event
    public void generateAlert(Alert alert) {
        notifyObservers(alert);
    }


    //getteri
    public String getIpAddress() {
        return ipAddress;
    }

    public Location getLocation() {
        return location;
    }

    public User getOwner() {
        return owner;
    }

    public String getHostname() {
        return hostname;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public Integer getCpuCores() {
        return cpuCores;
    }

    public Integer getRamGb() {
        return ramGb;
    }

    public Integer getStorageGb() {
        return storageGb;
    }

}