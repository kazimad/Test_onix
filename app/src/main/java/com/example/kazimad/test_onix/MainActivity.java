package com.example.kazimad.test_onix;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Handler;
import android.os.Bundle;

import com.example.kazimad.test_onix.data.DataStore;
import com.example.kazimad.test_onix.data.OnixProvider;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Activity activity;
    public static final int LOADER_USER_ID = 1;
    public boolean wasLoged = false;
    private LoaderManager.LoaderCallbacks loaderCallbacks;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        activity = this;
        loaderCallbacks = this;
        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.getLoaderManager().initLoader(LOADER_USER_ID, null, loaderCallbacks);

            }
        }, SPLASH_DISPLAY_LENGTH);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(activity, OnixProvider.getContentUri(DataStore.SaveUser.CONTENT_URI), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (cursor.getCount()) {
            case 0:
                if (!wasLoged) {
                    goToLoginActivity();
                    wasLoged = true;
                    finish();
                }
                break;
            case 1:
                if (!wasLoged) {
                    Intent drawerIntent = new Intent(activity, NavigationDrawerActivity.class);
                    activity.startActivity(drawerIntent);
//                activity.finish();
                    wasLoged = true;
                    finish();
                }
                break;
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void goToLoginActivity() {
        Intent loginIntent = new Intent(activity, LoginActivity.class);
        activity.startActivity(loginIntent);
    }
}
