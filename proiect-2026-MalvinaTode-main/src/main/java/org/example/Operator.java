package org.example;

public class Operator extends User{
    private String departament;

    public Operator(String name, String role, String email, String departament) {
        super(name, role, email);
        this.departament = departament;
    }

    //getter
    public String getDepartament() {
        return departament;
    }
}
