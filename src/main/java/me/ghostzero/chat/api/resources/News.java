package me.ghostzero.chat.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    private int id;
    private String title;
    private String text;
    private String headerimg;
    private String thumbimg;
    private int authorid;
    private long timestamp;
    private boolean published;
    private String nickname;
    private String imgthumb;
    private int comments;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNickname() {
        return nickname;
    }

    public String getThumbimg() {
        return thumbimg;
    }

    public String getThumbimgUrl() {
        return String.format("https://anime.academy/img/Assets/newsimg/thumbnail/%s", getThumbimg());
    }

    public int getComments() {
        return comments;
    }

    public String getText() {
        return text;
    }
}
