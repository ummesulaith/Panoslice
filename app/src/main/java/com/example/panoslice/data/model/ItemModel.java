package com.example.panoslice.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemModel {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;



    @SerializedName("owner")
    @Expose
    private Owner owner;


    @SerializedName("watchers_count")
    @Expose
    private Integer watchersCount;


    public ItemModel(String name, String fullName, Owner owner, Integer watchersCount) {
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.watchersCount = watchersCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
    }
}
