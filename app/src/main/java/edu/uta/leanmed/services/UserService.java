package edu.uta.leanmed.services;

import edu.uta.leanmed.pojo.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vaibhav's Console on 3/9/2019.
 */

public interface UserService {
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String email, @Query("password") String password);

    @GET("users/{user}/forgotpass")
    Call<Boolean> forgotPass(@Path("user") String email);

    @POST("users")
    Call<Boolean> addUser(@Body User user);
}
