package com.pavel_kaminsky.googlehome_broadlink_bridge;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mob41.blapi.BLDevice;
import com.github.mob41.blapi.RM2Device;
import com.github.mob41.blapi.mac.Mac;
import com.pavel_kaminsky.googlehome_broadlink_bridge.firebase.Command;
import com.pavel_kaminsky.googlehome_broadlink_bridge.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.message)
    TextView mTextMessage;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;

                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;

                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Command event) {
        mTextMessage.setText(mTextMessage.getText() + "\n [" + event.getDate() + "]" + event.getCommand());
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        NetworkUtils.scanNetwork();
//        new DiscoverTask().execute();
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
