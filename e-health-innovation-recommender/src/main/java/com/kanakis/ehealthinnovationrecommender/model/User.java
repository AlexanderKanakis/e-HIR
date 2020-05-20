package com.kanakis.ehealthinnovationrecommender.model;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {

    private final UUID id;


    public User(UUID id) {
        this.id = id;
    }


    public UUID getId() {
        return id;
    }


}
