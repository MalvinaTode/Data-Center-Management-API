package org.example;

/*interfata pentru subiect in patternul observer*/
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(Alert alert);
}
