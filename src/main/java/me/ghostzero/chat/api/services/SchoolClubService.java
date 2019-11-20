package me.ghostzero.chat.api.services;

import me.ghostzero.chat.api.resources.SchoolClubPost;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import java.util.List;

public interface SchoolClubService {

    @Headers({"Accept: application/json"})
    @GET("data/schoolclub/allposts")
    public Call<List<SchoolClubPost>> getAllPosts();

}
