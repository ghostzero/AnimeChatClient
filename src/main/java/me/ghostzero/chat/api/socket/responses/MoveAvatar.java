package me.ghostzero.chat.api.socket.responses;

import me.ghostzero.chat.api.socket.events.Event;

public class MoveAvatar extends Event {

    private Position finalPositionSmall;
    private Position finalPositionBig;
    private boolean isArrow;
    private String user;
    private String userid;
    private String direction;

    public Position getFinalPositionSmall() {
        return finalPositionSmall;
    }

    public Position getFinalPositionBig() {
        return finalPositionBig;
    }

    public String getUser() {
        return user;
    }

    public String getUserId() {
        return userid;
    }
}
