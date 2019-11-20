package me.ghostzero.chat.api.services;

import me.ghostzero.chat.api.resources.LoggedInUser;
import me.ghostzero.chat.api.resources.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import java.util.List;

public interface UserService {
    @Headers({"Accept: application/json"})
    @GET("data/OwnUsername")
    public Call<LoggedInUser> getOwnUsername();

    @Headers({"Accept: application/json"})
    @GET("data/users")
    public Call<List<User>> getUsers();
}
