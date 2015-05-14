package com.example.kazimad.test_onix.network.response;

import com.example.kazimad.test_onix.models.Item;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Kazimad on 07.05.2015.
 */
public class ItemResponse {
    @Expose
    @SerializedName("success")
    ArrayList<Item> itemArrayList;

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }
}
