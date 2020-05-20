package com.kanakis.ehealthinnovationrecommender.model;

import java.util.UUID;

public class UserInternal extends User {

    private String name;
    private  final String email;
    private  final String password;
    private final int privileges;

    public UserInternal(UUID id, String name, String email, String password, int privileges ) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.privileges = privileges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPrivileges() {
        return privileges;
    }
}
