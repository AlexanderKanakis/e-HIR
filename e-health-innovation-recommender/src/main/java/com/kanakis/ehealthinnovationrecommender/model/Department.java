package com.kanakis.ehealthinnovationrecommender.model;

public class Department {

    final int id;
    final String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
