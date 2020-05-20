package com.kanakis.ehealthinnovationrecommender.model;

public class Item {
    private final int id;
    private final String name;
    private final String description;
    private final String links;
    private int imageId;

    public Item(int id, String name, String description, String links, int imageId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.links = links;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLinks() {
        return links;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
