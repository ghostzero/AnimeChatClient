package me.ghostzero.chat.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoggedInUser {

    private int userid;
    private int activitypoints;
    private String festerusername;
    private String profileimg;
    private String username;

    public String getFesterusername() {
        return festerusername;
    }

    public int getActivitypoints() {
        return activitypoints;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public String getProfileimgUrl() {
        return String.format("https://anime.academy/img/Assets/profile/gallery/%s", getProfileimg());
    }

    public String getUsername() {
        return username;
    }

    public int getUserid() {
        return userid;
    }
}
