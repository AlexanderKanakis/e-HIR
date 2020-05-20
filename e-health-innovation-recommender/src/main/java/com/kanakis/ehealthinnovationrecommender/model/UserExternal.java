package com.kanakis.ehealthinnovationrecommender.model;

import java.util.UUID;

public class UserExternal extends User {

    private int countryID;
    private int departmentID;
    private int occupationID;

    public UserExternal(UUID id, int countryID, int departmentID, int occupationID) {
        super(id);
        this.countryID = countryID;
        this.departmentID = departmentID;
        this.occupationID = occupationID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getOccupationID() {
        return occupationID;
    }

    public void setOccupationID(int occupationID) {
        this.occupationID = occupationID;
    }
}
