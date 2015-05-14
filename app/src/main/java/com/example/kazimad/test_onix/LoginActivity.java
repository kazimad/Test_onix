package com.example.kazimad.test_onix;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * Kazimad on 29.04.2015.
 */
public class LoginActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().add(R.id.login_container, new LoginFragment()).commit();
    }

}
