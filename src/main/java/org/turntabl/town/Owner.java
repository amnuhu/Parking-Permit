package org.turntabl.town;

public class Owner {


    private boolean registered;
    private String nationalID;
    private String name;

    public Owner(String nationalID, String name) {
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
}
