package org.deadlock.oim.data;

public class Data_account {
    public String name,email;

    public Data_account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
