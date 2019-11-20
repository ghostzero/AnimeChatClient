package me.ghostzero.chat.api.resources;

public class User {
    private int userid;
    private int activitypoints;
    private String festerusername;
    private String username;

    public String getFesterusername() {
        return festerusername;
    }

    public int getActivitypoints() {
        return activitypoints;
    }

    public String getUsername() {
        return username;
    }

    public int getUserid() {
        return userid;
    }
}
