```java
package me.ghostzero.chat;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import me.ghostzero.chat.api.ChatClient;
import me.ghostzero.chat.api.socket.Event;
import me.ghostzero.chat.api.socket.SocketClient;
import me.ghostzero.chat.api.socket.emitters.AsyncEmit;
import me.ghostzero.chat.api.socket.events.EventHandler;
import me.ghostzero.chat.api.socket.events.EventListener;
import me.ghostzero.chat.api.socket.responses.ChatLine;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
public class ChatClientUnitTest implements EventListener {

    private SocketClient socketClient;
    private ChatClient chatClient;

    @Test
    public void testSendCommand() throws Exception {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://hitagi.ghostzero.moe/api/exec")
            .field("username", "Twillux")
            .field("args", ".help")
            .asJson();

        String message = jsonResponse.getBody().getObject().getString("message");

        System.out.println("Message: " + message);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testChatClient() throws Exception {
        chatClient = new ChatClient.Builder()
            .withSessionId("s%3AttZ7YKcPFPwkBjRYBsn4wHqaUumt5MsY.X2QZ23jd8Y0YwFxUmV5Lo9DAyRtNFZew2%2FnWMQeJVQ8")
            .build();

        socketClient = chatClient.buildSocketClient();

        socketClient.getEventManager().registerEvents(this);

        socketClient.getSocket().on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connected to Socket!");

                socketClient.joinRoom("Strand");
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Disconnected from Socket.");
            }
        }).on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("System message: " + args[0].toString());
            }
        });

        socketClient.connect();

        System.in.read();
    }

    @EventHandler
    public void onChatLine(ChatLine event) {
        System.out.println(String.format("Chat Event {name: %s, line: %s}", event.getUser(), event.getChatLine()));

        // block system messages
        if (event.getFestername() == null) return;
        // block message processing from hitagi and commands
        if (event.getFestername().equals("Hitagi") || !event.getChatLine().startsWith(".")) return;

        try {
            System.out.println(String.format("[Waifu Handler] Exec '%s' as %s", event.getChatLine(), event.getFestername()));
            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://hitagi.ghostzero.moe/api/exec")
                .field("username", event.getFestername())
                .field("args", event.getChatLine())
                .asJson();

            JSONObject jsonObject = jsonResponse.getBody().getObject();

            if (jsonObject.has("message") && jsonObject.getString("message").length() > 0) {
                final String message = jsonObject.getString("message");
                System.out.println("[Waifu Handler] Reply back: " + message);
                socketClient.emitAsync(new AsyncEmit() {
                    @Override
                    public void run() throws Exception {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("chatLine", message);
                        socketClient.getSocket().emit(Event.EVENT_NEW_CHAT_LINE, jsonObject);
                    }
                }, 0);
            }

        } catch (UnirestException e) {
            System.out.println("[Waifu Handler] Error exec (unirest e): " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("[Waifu Handler] Error exec (json e): " + e.getMessage());
        }
    }
}

```
