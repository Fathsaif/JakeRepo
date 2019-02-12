package com.saif.jakerepo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.acl.Owner;

public class Repos implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("owner")
    @Expose
    private com.saif.jakerepo.models.Owner owner;

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

    public com.saif.jakerepo.models.Owner getOwner() {
        return owner;
    }

    public void setOwner(com.saif.jakerepo.models.Owner owner) {
        this.owner = owner;
    }
}
