package com.kanakis.ehealthinnovationrecommender.model;

public class ItemImage {

    private int id;

    private String name;

    private String type;

    private byte[] picByte;

    public ItemImage(int id, String name, String type, byte[] picByte) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public ItemImage(String name, String type, byte[] picByte) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public int getId() {
        return id;
    }
}
