package com.example.kazimad.test_onix.util;

import android.app.Application;
import com.example.kazimad.test_onix.network.RestClient;

/**
 * Kazimad on 31.03.2015.
 */
public class App extends Application {
    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        return restClient;
    }
}
