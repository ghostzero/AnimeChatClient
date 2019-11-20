package me.ghostzero.chat.api.socket.responses;

import me.ghostzero.chat.api.socket.events.Event;

public class ChatLine extends Event {
    private int userid;
    private long timestamp;
    private String festername;
    private String user;
    private String chatLine;
    private String bubbleColorBG;
    private String bubbleColorText;

    public ChatLine(String name, String color) {
        this.user = name;
        this.festername = name;
        this.bubbleColorText = color;
    }

    public String getUser() {
        return user;
    }

    public String getFestername() {
        return festername;
    }

    public String getChatLine() {
        return chatLine;
    }

    public String getBubbleColorBG() {
        if(bubbleColorBG == null) {
            return "#333333";
        }

        return bubbleColorBG.replace("0x", "#");
    }

    public String getBubbleColorText() {
        if(bubbleColorText == null) {
            return "#333333";
        }

        return bubbleColorText.replace("0x", "#");
    }
}