package com.example.kazimad.test_onix.network;



import com.example.kazimad.test_onix.network.response.ItemResponse;
import com.example.kazimad.test_onix.network.response.UserResponse;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Kazimad on 31.03.2015.
 */
public interface WorkWithApi {
    @GET("/s/joq65yt0f2xz7sd/JSON_onix.txt")
    void getItems(Callback<ItemResponse> itemResponseCallback);
    @GET("/s/ntqjmxln43xterh/JSON_onix%20-2.txt")
    void getItems_2(Callback<ItemResponse> itemResponseCallback);
    @GET("/s/nmyndriz3ltluan/USER_onix.txt")
    void getUser(Callback<UserResponse> userResponseCallback);
}