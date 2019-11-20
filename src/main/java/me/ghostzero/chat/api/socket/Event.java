package me.ghostzero.chat.api.socket;

public class Event {

    public static final String EVENT_MESSAGE = "message";
    public static final String EVENT_START_TUTORIAL = "startTutorial";
    public static final String EVENT_REQUEST_ROOM_LIST = "requestRoomList";
    public static final String EVENT_INIT_ON_JOIN = "initOnJoin";
    public static final String EVENT_AVATAR_LEAVE = "avatarLeave";
    public static final String EVENT_LOAD_NEW_AVATAR = "loadNewAvatar";
    public static final String EVENT_LOAD_CHATTER_LIST = "loadChatterlist";
    public static final String EVENT_UPDATE_LOGGED_IN_USERS = "updateLoggedInUsers";
    public static final String EVENT_REMOVE_FROM_CHATTER_LIST = "removeFromChatterlist";
    public static final String EVENT_ADD_USER_TO_CHATTER_LIST = "addUserToChatterlist";
    public static final String EVENT_SUBTRACT_ROOM_LENGTH = "subtractRoomLength";
    public static final String EVENT_ADD_JOINED_ROOM_LENGTH = "addJoinedRoomLength";
    public static final String EVENT_UPDATE_USERS_ROOM = "updateUsersRoom";
    public static final String EVENT_GET_FRIENDS = "getFriends";
    public static final String EVENT_MOVE_AVATAR = "moveAvatar";
    public static final String EVENT_DESTROY_SIGN = "destroySign";
    public static final String EVENT_UPDATE_CHAT_LINES = "updateChatLines";
    public static final String EVENT_UPDATE_NUMBER_UNREAD_PN = "updateNumberUnreadPN";
    public static final String EVENT_IS_TYPING = "isTyping";
    public static final String EVENT_STOP_TYPING = "stopTyping";
    public static final String EVENT_NEW_RANK = "newRank";

    /**
     * Emittable
     */
    public static final String EVENT_GET_TOPICOMAT = "getTopicomat";
    public static final String EVENT_JOIN_ROOM = "joinRoom";
    public static final String EVENT_NEW_CHAT_LINE = "newChatLine";
}
