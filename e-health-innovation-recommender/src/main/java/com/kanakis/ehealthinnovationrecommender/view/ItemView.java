package com.kanakis.ehealthinnovationrecommender.view;

import javax.validation.constraints.NotNull;

public class ItemView {

    @NotNull
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String links;

    public ItemView(int id, String name, String description, String links) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
