package com.example.kazimad.test_onix;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.example.kazimad.test_onix.data.DataStore;
import com.example.kazimad.test_onix.data.OnixProvider;
import com.example.kazimad.test_onix.navigationDrawerSections.Fragment_1;
import com.example.kazimad.test_onix.navigationDrawerSections.Fragment_2;


public class NavigationDrawerActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private NavigationDrawerFragment mNavigationDrawerFragment;


    public static final String DEFAULT_INDEX = "defaultIndex";
    public static final int DEFAULT_FRAGMENT = 0;
    public static final int DEFAULT_FRAGMENT_SETTINGS = 2;
    private CharSequence mTitle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        toolbar.setTitle(mTitle);
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        if (getIntent().getIntExtra(DEFAULT_INDEX, DEFAULT_FRAGMENT) == DEFAULT_FRAGMENT_SETTINGS) {
            onNavigationDrawerItemSelected(DEFAULT_FRAGMENT_SETTINGS);
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_1()).commit();
                mTitle = getString(R.string.fragment_1);
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.container, new Fragment_2()).commit();
                mTitle = getString(R.string.fragment_2);
                break;
            case 2:
                getContentResolver().delete(OnixProvider.getContentUri(DataStore.SaveUser.CONTENT_URI), null, null);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {

            getMenuInflater().inflate(R.menu.navigation_drawer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void restoreActionBar() {
        toolbar.setTitle(mTitle);
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));

    }

}
