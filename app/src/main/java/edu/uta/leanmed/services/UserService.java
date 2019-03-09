package edu.uta.leanmed.services;

import edu.uta.leanmed.pojo.UserPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vaibhav's Console on 3/9/2019.
 */

public interface UserService {
    @GET("users/{user}")
    Call<UserPojo> getUser(@Path("emailId") String email, @Query("password") String password);

    @GET("users/{user}/forgotpass")
    Call<Boolean> forgotPass(@Path("emailId") String email);
}
