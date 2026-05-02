package org.example;

//interfata pentru comenzi
public interface Command {
    void execute(String[] params, int lineNumber);
}
