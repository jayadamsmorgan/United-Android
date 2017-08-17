package com.berdnikovllc.unitedsocialnetwork.united;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FeedFragment.OnFragmentInteractionListener,
        MessagesFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    FloatingActionButton fabNewPost, fabNewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Feed");
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,
                new FeedFragment(), "FEED_FRAGMENT").commit();

        fabNewPost = findViewById(R.id.fab_new_post);
        fabNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
        fabNewMessage = findViewById(R.id.fab_new_message);
        fabNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        SelectReceiversActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handling navigation view item clicks.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_feed) {               // FEED
            // Prevention of reloading fragment if it has selected already
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("FEED_FRAGMENT");
            if (!(fragment != null && fragment.isVisible())) {
                fabNewMessage.hide();
                fabNewPost.show();
                setTitle(R.string.feed);
                fragmentManager.beginTransaction().replace(R.id.content_frame,
                        new FeedFragment(), "FEED_FRAGMENT").commit();
            }
        } else if (id == R.id.nav_messages) {    // MESSAGES
            // Prevention of reloading fragment if it has selected already
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentByTag("MESSAGES_FRAGMENT");
            if (!(fragment != null && fragment.isVisible())) {
                fabNewPost.hide();
                fabNewMessage.show();
                setTitle(R.string.messages);
                fragmentManager.beginTransaction().replace(R.id.content_frame,
                        new MessagesFragment(), "MESSAGES_FRAGMENT").commit();
            }
        } else if (id == R.id.nav_photos) {      // PHOTOS
            // Prevention of reloading fragment if it has selected already
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentByTag("PHOTOS_FRAGMENT");
            if (!(fragment != null && fragment.isVisible())) {
                fabNewMessage.hide();
                fabNewPost.hide();
                setTitle(R.string.photos);
                fragmentManager.beginTransaction().replace(R.id.content_frame,
                        new PhotosFragment(), "PHOTOS_FRAGMENT").commit();
            }
        } else if (id == R.id.nav_settings) {    // SETTINGS
            // Prevention of reloading fragment if it has selected already
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentByTag("SETTINGS_FRAGMENT");
            if (!(fragment != null && fragment.isVisible())) {
                fabNewMessage.hide();
                fabNewPost.hide();
                setTitle(R.string.settings);
                fragmentManager.beginTransaction().replace(R.id.content_frame,
                        new SettingsFragment(), "SETTINGS_FRAGMENT").commit();
            }
        } else if (id == R.id.nav_send_bug) {    // BUGREPORT
            fabNewPost.hide();
            fabNewMessage.hide();
            Intent intent = new Intent(MainActivity.this,
                    SendBugReportActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
