package edu.uta.leanmed.services;

import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vaibhav's Console on 3/9/2019.
 */

public class RetrofitService {
    private Retrofit retrofit;
    private static RetrofitService retrofitService;
    private RetrofitService(){
        retrofit=new Retrofit.Builder()
                .baseUrl("https://192.168.1.1/leanmed/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static Retrofit newInstance() {
        if(retrofitService==null)
            retrofitService=new RetrofitService();
        return retrofitService.retrofit;
    }

}
