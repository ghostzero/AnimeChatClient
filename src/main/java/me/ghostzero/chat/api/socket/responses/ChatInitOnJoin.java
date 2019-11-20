package me.ghostzero.chat.api.socket.responses;

import me.ghostzero.chat.api.socket.events.Event;

import java.util.List;

public class ChatInitOnJoin extends Event {

    private String roomBG;
    private List<Room> rooms;
    private List<Avatar> avatare;

    public String getRoomBG() {
        return roomBG;
    }

    public String getRoomBGUrl() {
        return String.format("https://anime.academy/img/Chatgfx/bg/%s", getRoomBG());
    }

    public List<Avatar> getAvatare() {
        return avatare;
    }
}
