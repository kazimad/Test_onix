package com.example.kazimad.test_onix.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Kazimad on 31.03.2015.
 */


public class RestClient {
    public static final String BASE_URL = "https://dl.dropboxusercontent.com";

    private WorkWithApi workWithApi;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();
        workWithApi = restAdapter.create(WorkWithApi.class);
    }

    public WorkWithApi getFromApi() {
        return workWithApi;
    }
}

