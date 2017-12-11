package com.pavel_kaminsky.googlehome_broadlink_bridge;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.home.HomeFragment;
import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.logs.LogsFragment;
import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.setup.SetupFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        SetupFragment.OnFragmentInteractionListener,
        LogsFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            changeFragment(item.getItemId());
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeFragment(R.id.navigation_home);
    }

    private void changeFragment(int fragment_id) {

        Fragment newFragment = null;
        switch (fragment_id) {
            case R.id.navigation_setup:
                newFragment = new SetupFragment();
                break;
            case R.id.navigation_home:
                newFragment = new HomeFragment();
                break;
            default:
                newFragment = new LogsFragment();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, newFragment)
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}