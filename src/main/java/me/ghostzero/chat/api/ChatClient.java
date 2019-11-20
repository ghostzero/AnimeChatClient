package me.ghostzero.chat.api;

import me.ghostzero.chat.api.resources.LoggedInUser;
import me.ghostzero.chat.api.resources.News;
import me.ghostzero.chat.api.services.NewsService;
import me.ghostzero.chat.api.services.UserService;
import me.ghostzero.chat.api.socket.SocketClient;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ChatClient {

    private static final String BASE_URI = "https://anime.academy/";

    private final Retrofit retrofit;
    private final String sessionId;

    private ChatClient(String sessionId) {
        this.sessionId = sessionId;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                if (ChatClient.this.sessionId == null) {
                    return chain.proceed(chain.request());
                }

                Request.Builder builder = chain.request().newBuilder();
                builder.addHeader("Cookie", "id=" + ChatClient.this.sessionId);
                return chain.proceed(builder.build());
            }
        }).build();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URI).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
    }

    public List<News> getNews() {
        try {
            return retrofit.create(NewsService.class).getNews().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public News getNews(int id) {
        try {
            return retrofit.create(NewsService.class).getNews(id).execute().body().get(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LoggedInUser getUser() {
        try {
            return retrofit.create(UserService.class).getOwnUsername().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SocketClient buildSocketClient() throws URISyntaxException {
        return new SocketClient.Builder()
                .withSessionId(sessionId)
                .withChatClient(this)
                .build();
    }

    public static class Builder {
        private String sessionId;

        public Builder withSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public ChatClient build() {
            return new ChatClient(sessionId);
        }
    }
}
