package com.example.kazimad.test_onix;


import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kazimad.test_onix.data.DataStore;
import com.example.kazimad.test_onix.data.OnixProvider;
import com.example.kazimad.test_onix.models.User;
import com.example.kazimad.test_onix.network.response.UserResponse;
import com.example.kazimad.test_onix.util.App;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Kazimad on 29.04.2015.
 */
public class LoginFragment extends Fragment {

    private MaterialEditText loginEditText;
    private MaterialEditText passEditText;
    private RelativeLayout progressLayout;
    private boolean allowLogin = false;
    private Button loginButton;
    private Button cancelButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginEditText = (MaterialEditText) view.findViewById(R.id.login_edit_text);
        passEditText = (MaterialEditText) view.findViewById(R.id.pass_edit_text);
        loginButton = (Button) view.findViewById(R.id.button_login);
        cancelButton = (Button) view.findViewById(R.id.button_cancel);
        progressLayout = (RelativeLayout) view.findViewById(R.id.progress_login_layout);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                if (allowLogin) {
                    progressLayout.setVisibility(View.VISIBLE);
                    setControlsEnabled(false);
                    App.getRestClient().getFromApi().getUser(new Callback<UserResponse>() {

                        @Override
                        public void success(UserResponse userResponse, Response response) {
                            progressLayout.setVisibility(View.GONE);
                            User user = userResponse.getUser();
                            addToBase(user.getLogin(), user.getPass());
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            progressLayout.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), getString(R.string.network_connection_failed), Toast.LENGTH_LONG).show();
                        }
                    });
                }
//               Toast.makeText(getActivity(), "button click ", Toast.LENGTH_SHORT).show();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginEditText.getText().clear();
                passEditText.getText().clear();
                loginEditText.requestFocus();
                loginEditText.setError(null);
                passEditText.setError(null);
            }
        });
        return view;
    }

    private void setControlsEnabled(boolean enabled) {
        cancelButton.setEnabled(enabled);
        loginButton.setEnabled(enabled);
        loginEditText.setEnabled(enabled);
        passEditText.setEnabled(enabled);
    }


    public void attemptLogin() {


        // Reset errors.
        loginEditText.setError(null);
        passEditText.setError(null);

        // Store values at the time of the login attempt.
        String login = loginEditText.getText().toString();
        String password = passEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            passEditText.setError(getString(R.string.error_field_required));
            focusView = passEditText;
            cancel = true;
        }

        if (TextUtils.isEmpty(login)) {
            loginEditText.setError(getString(R.string.error_field_required));
            focusView = loginEditText;
            cancel = true;
        }
        if (password.length() < 3) {
            passEditText.setError(getString(R.string.error_pass_length));
            focusView = passEditText;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            allowLogin = true;
            //            Toast.makeText(getActivity(), "запрос в сеть", Toast.LENGTH_LONG).show();

        }
    }

    public void addToBase(String login, String pass) {
        Uri uri = OnixProvider.getContentUri(DataStore.SaveUser.CONTENT_URI);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataStore.SaveUser.LOG_IN, login);
        contentValues.put(DataStore.SaveUser.PASS, pass);
        getActivity().getContentResolver().insert(uri, contentValues);
        Intent intent = new Intent(getActivity(), NavigationDrawerActivity.class);
        startActivity(intent);
    }


}
