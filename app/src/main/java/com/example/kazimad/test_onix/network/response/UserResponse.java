package com.example.kazimad.test_onix.network.response;

import com.example.kazimad.test_onix.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Kazimad on 07.05.2015.
 */
public class UserResponse {

    @Expose
    @SerializedName("success")
    User user;

    public User getUser() {
        return user;
    }
}
