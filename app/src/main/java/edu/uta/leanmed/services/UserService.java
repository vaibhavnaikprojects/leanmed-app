package edu.uta.leanmed.services;

import edu.uta.leanmed.pojo.UserPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vaibhav's Console on 3/9/2019.
 */

public interface UserService {
    @GET("users")
    Call<UserPojo> getUser(@Query("emailId") String email,@Query("password") String password);
}
