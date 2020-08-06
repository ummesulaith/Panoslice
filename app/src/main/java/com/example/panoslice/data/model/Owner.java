package com.example.panoslice.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("avatar_url")
    @Expose
    private String avatarURL;

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Owner(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
