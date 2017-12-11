package com.pavel_kaminsky.googlehome_broadlink_bridge;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.github.mob41.blapi.BLDevice;
import com.github.mob41.blapi.RM2Device;
import com.github.mob41.blapi.mac.Mac;
import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.logs.LogsFragment;
import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.setup.SetupFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SetupFragment.OnFragmentInteractionListener, LogsFragment.OnFragmentInteractionListener {

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
        changeFragment(R.id.navigation_setup);
    }

    private void changeFragment(int fragment_id) {

        Fragment newFragment = null;
        if (fragment_id == R.id.navigation_setup) {
            newFragment = new SetupFragment();
        } else {
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


class DiscoverTask extends AsyncTask {

    protected Object doInBackground(Object[] objects) {
        try {
//            BLDevice[] devs = BLDevice.discoverDevices(5000);
            BLDevice dev = new RM2Device("10.0.0.9", new Mac("34:ea:34:88:f8:61"));

//            if (devs == null || devs.length == 0) {
//                return null;
//            }


//            for (int i = 0; i < devs.length; i++) {
//                Log.d("api", "Device " + i + ": " + devs[i].getHost() + " / " + devs[i].getMac() + " / " + Integer.toHexString(devs[i].getDeviceType()));
//            }

//            BLDevice dev = devs[0];

            dev.auth();

            if (dev instanceof RM2Device) {
                RM2Device rm = (RM2Device) dev;
//            rm.enterLearning();
            } else {
                Log.w("api", "The device is not a RM device. Aborting RM staff");
            }

            dev.close();

        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
