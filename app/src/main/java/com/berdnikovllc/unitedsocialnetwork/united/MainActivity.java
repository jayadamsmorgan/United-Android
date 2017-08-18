package com.berdnikovllc.unitedsocialnetwork.united;

import android.annotation.SuppressLint;
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
        implements
        NavigationView.OnNavigationItemSelectedListener,

        FeedFragment.OnFragmentInteractionListener,
        FeedbackFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener,
        MessagesFragment.OnFragmentInteractionListener,
        FriendsFragment.OnFragmentInteractionListener,
        CommunitiesFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener,
        VideosFragment.OnFragmentInteractionListener,
        LikedFragment.OnFragmentInteractionListener,
        YourAccountsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    FloatingActionButton fabNewPost, fabNewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Feed");
        loadFragment("FEED_FRAGMENT", R.string.feed, new FeedFragment());

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

    private void loadFragment(String fragmentTag, int titleId, Fragment fragment) {
        if (!(fragment != null && fragment.isVisible())) {
            if (fabNewPost != null && fabNewMessage != null) {
                switch (fragmentTag) {
                    case "FEED_FRAGMENT":
                        fabNewMessage.hide();
                        fabNewPost.show();
                        break;
                    case "MESSAGES_FRAGMENT":
                        fabNewPost.hide();
                        fabNewMessage.show();
                        break;
                    default:
                        fabNewMessage.hide();
                        fabNewPost.hide();
                        break;
                }
            }
            setTitle(titleId);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handling navigationView's item clicks.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_feed:
                loadFragment("FEED_FRAGMENT", R.string.feed, new FeedFragment());
                break;
            case R.id.nav_feedback:
                loadFragment("FEEDBACK_FRAGMENT", R.string.feedback, new FeedbackFragment());
                break;
            case R.id.nav_search:
                loadFragment("SEARCH_FRAGMENT", R.string.search, new SearchFragment());
                break;
            case R.id.nav_messages:
                loadFragment("MESSAGES_FRAGMENT", R.string.messages, new MessagesFragment());
                break;
            case R.id.nav_friends:
                loadFragment("FRIENDS_FRAGMENT", R.string.friends, new FriendsFragment());
                break;
            case R.id.nav_communities:
                loadFragment("COMMUNITIES_FRAGMENT", R.string.communities,
                        new CommunitiesFragment());
                break;
            case R.id.nav_photos:
                loadFragment("PHOTOS_FRAGMENT", R.string.photos, new PhotosFragment());
                break;
            case R.id.nav_videos:
                loadFragment("VIDEOS_FRAGMENT", R.string.videos, new VideosFragment());
                break;
            case R.id.nav_liked:
                loadFragment("LIKED_FRAGMENT", R.string.liked, new LikedFragment());
                break;
            case R.id.nav_your_accounts:
                loadFragment("YOUR_ACCOUNTS_FRAGMENT", R.string.your_accounts,
                        new YourAccountsFragment());
                break;
            case R.id.nav_settings:
                loadFragment("SETTINGS_FRAGMENT", R.string.settings, new SettingsFragment());
                break;
            case R.id.nav_send_bug:
                Intent intent = new Intent(MainActivity.this,
                        SendBugReportActivity.class);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
