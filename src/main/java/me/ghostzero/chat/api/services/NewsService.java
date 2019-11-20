package me.ghostzero.chat.api.services;

import me.ghostzero.chat.api.resources.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.List;

public interface NewsService {

    @Headers({"Accept: application/json"})
    @GET("data/news/public")
    public Call<List<News>> getNews();

    @Headers({"Accept: application/json"})
    @GET("data/news/{id}")
    public Call<List<News>> getNews(@Path("id") int id);

}
