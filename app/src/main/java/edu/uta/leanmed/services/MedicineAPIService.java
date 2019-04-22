package edu.uta.leanmed.services;

import edu.uta.leanmed.pojo.InventoryResponse;
import edu.uta.leanmed.pojo.User;
import edu.uta.leanmed.pojo.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vaibhav's Console on 4/21/2019.
 */

public interface MedicineAPIService {
    @Headers("Auth-Key: leanmedapi")
    @GET("inventory/{query}")
    Call<InventoryResponse> getMedicineResponse(@Header("User-ID") String userId, @Header("Authorization") String auth,@Path("query") String query);
}
