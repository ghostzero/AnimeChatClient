package me.ghostzero.chat.api.socket.listeners;

import io.socket.emitter.Emitter;
import me.ghostzero.chat.api.socket.Event;
import me.ghostzero.chat.api.socket.SocketClient;
import me.ghostzero.chat.api.socket.responses.ChatInitOnJoin;
import me.ghostzero.chat.api.socket.responses.ChatLine;
import me.ghostzero.chat.api.socket.responses.MoveAvatar;

/**
 * This is for internal usage only.
 */
public class ListenerAdapter {

    public void with(final SocketClient socketClient) {

        socketClient.getSocket().on(Event.EVENT_UPDATE_CHAT_LINES, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                debug(Event.EVENT_UPDATE_CHAT_LINES, args);
                socketClient.getEventManager().fireEvent(args[0], ChatLine.class);
            }
        }).on(Event.EVENT_INIT_ON_JOIN, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                debug(Event.EVENT_INIT_ON_JOIN, args);
                socketClient.getEventManager().fireEvent(args[0], ChatInitOnJoin.class);
            }
        }).on(Event.EVENT_LOAD_NEW_AVATAR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                debug(Event.EVENT_LOAD_NEW_AVATAR, args);
            }
        }).on(Event.EVENT_AVATAR_LEAVE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                debug(Event.EVENT_AVATAR_LEAVE, args);
            }
        }).on(Event.EVENT_ADD_JOINED_ROOM_LENGTH, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                debug(Event.EVENT_ADD_JOINED_ROOM_LENGTH, args);
            }
        }).on(Event.EVENT_SUBTRACT_ROOM_LENGTH, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                debug(Event.EVENT_SUBTRACT_ROOM_LENGTH, args);
            }
        }).on(Event.EVENT_MOVE_AVATAR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                debug(Event.EVENT_MOVE_AVATAR, args);
                MoveAvatar e = (MoveAvatar) socketClient.getEventManager().fireEvent(args[0], MoveAvatar.class);
            }
        });
    }

    private void debug(String event, Object[] args) {
        if (args != null && args.length > 0) {
            System.out.println(String.format("[%s]: %s", event, args[0]));
        }
    }
}
