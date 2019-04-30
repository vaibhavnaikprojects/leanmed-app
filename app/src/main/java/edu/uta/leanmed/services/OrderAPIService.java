package edu.uta.leanmed.services;

import edu.uta.leanmed.pojo.Order;
import edu.uta.leanmed.pojo.OrderResponse;
import edu.uta.leanmed.pojo.User;
import edu.uta.leanmed.pojo.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OrderAPIService {
    @Headers("Auth-Key: leanmedapi")
    @POST("orders")
    Call<OrderResponse> placeOrder(@Header("User-ID") String userId, @Header("Authorization") String auth,@Body Order order);
}
