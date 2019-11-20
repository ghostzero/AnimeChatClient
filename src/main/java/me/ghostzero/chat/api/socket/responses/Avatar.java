package me.ghostzero.chat.api.socket.responses;

public class Avatar {
    private String currentava;
    private String fixedname;
    private String nameColor;
    private String imgthumb;
    private String userid;
    private String room;
    private String username;
    private Position positionBig;
    private Position positionSmall;

    public String getCurrentAva() {
        return currentava;
    }

    public String getCurrentAvaUrl() {
        return String.format("https://anime.academy/img/Chatgfx/skinuploads/%s", getCurrentAva());
    }

    public String getFixedName() {
        return fixedname;
    }

    public String getNameColor() {
        return nameColor;
    }

    public String getImgThumb() {
        return imgthumb;
    }

    public String getUserId() {
        return userid;
    }

    public String getRoom() {
        return room;
    }

    public String getUsername() {
        return username;
    }

    public Position getPositionBig() {
        return positionBig;
    }

    public Position getPositionSmall() {
        return positionSmall;
    }
}
