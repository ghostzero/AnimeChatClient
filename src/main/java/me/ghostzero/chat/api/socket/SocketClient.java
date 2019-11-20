package me.ghostzero.chat.api.socket;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import me.ghostzero.chat.api.ChatClient;
import me.ghostzero.chat.api.resources.MemberData;
import me.ghostzero.chat.api.socket.emitters.AsyncEmit;
import me.ghostzero.chat.api.socket.events.EventManager;
import me.ghostzero.chat.api.socket.listeners.ListenerAdapter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class SocketClient {
    /**
     * Base URL of the HetznerCloud-API
     */
    private static final String BASE_URI = "https://anime.academy";

    private static final String TAG = "SocketClient";

    private final ChatClient chatClient;
    private final String sessionId;
    private final Socket socket;
    private MemberData consoleSender;
    private EventManager eventManager;

    private SocketClient(ChatClient chatClient, String sessionId) throws URISyntaxException {
        this.chatClient = chatClient;
        this.sessionId = sessionId;

        this.eventManager = new EventManager();
        new ListenerAdapter().with(this);
        this.consoleSender = new MemberData("System", "#e91e63");

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                if (SocketClient.this.sessionId == null) {
                    return chain.proceed(chain.request());
                }

                System.out.println(TAG + " Set Cookie with ID=" + SocketClient.this.sessionId);

                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Cookie", "id=" + SocketClient.this.sessionId);
                return chain.proceed(builder.build());
            }
        }).build();

        // default settings for all sockets
        IO.setDefaultOkHttpWebSocketFactory(okHttpClient);
        IO.setDefaultOkHttpCallFactory(okHttpClient);

        IO.Options opts = new IO.Options();
        opts.callFactory = okHttpClient;
        opts.webSocketFactory = okHttpClient;

        socket = IO.socket(BASE_URI, opts);

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println(TAG + " Connected to socket.io!");
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println(TAG + " Disconnected from socket.io!");
            }

        });

        socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(TAG + " Message: " + args[0]);
            }
        });
    }

    public void connect() {
        this.socket.connect();
    }

    public boolean connected() {
        return this.socket.connected();
    }

    public void disconnect() {
        this.socket.disconnect();
    }

    public Socket getSocket() {
        return socket;
    }

    public MemberData getConsoleSender() {
        return consoleSender;
    }

    public void joinRoom(final String room) {
        emitAsync(new AsyncEmit() {
            @Override
            public void run() throws Exception {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("room", room);
                System.out.println(TAG + " Emit join room: " + jsonObject);
                getSocket().emit(Event.EVENT_JOIN_ROOM, jsonObject);
            }
        }, 1000);
    }

    public void emitAsync(final AsyncEmit asyncEmit, final int sleep) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    asyncEmit.exec(sleep);
                } catch (Exception e) {
                    System.out.println(TAG + " Emit exception: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public static class Builder {
        private String sessionId;
        private ChatClient chatClient;

        public Builder withSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder withChatClient(ChatClient chatClient) {
            this.chatClient = chatClient;
            return this;
        }

        public SocketClient build() throws URISyntaxException {
            return new SocketClient(chatClient, sessionId);
        }
    }
}
