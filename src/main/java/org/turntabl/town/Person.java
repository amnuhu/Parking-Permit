package org.turntabl.town;

import java.util.Objects;

public class Person {


    private boolean registered;
    private String nationalID;
    private String name;



    public Person(String nationalID, String name) {
        this.nationalID = nationalID;
        this.name = name;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isRegistered() {
        return registered;
    }

    public String getNationalID() {
        return nationalID;
    }

    public String getName() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return nationalID.equals(person.nationalID) && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalID, name);
    }
}
