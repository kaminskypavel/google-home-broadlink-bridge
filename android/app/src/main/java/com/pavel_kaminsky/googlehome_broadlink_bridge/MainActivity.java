package com.pavel_kaminsky.googlehome_broadlink_bridge;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.home.HomeFragment;
import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.logs.LogsFragment;
import com.pavel_kaminsky.googlehome_broadlink_bridge.fragments.setup.SetupFragment;
import com.pavel_kaminsky.googlehome_broadlink_bridge.models.Command;
import com.pavel_kaminsky.googlehome_broadlink_bridge.utils.RMBridgeHttpAsync;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        SetupFragment.OnFragmentInteractionListener,
        LogsFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        changeFragment(item.getItemId());
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Command cmd) {
        switch (cmd.getCommand().toLowerCase()) {
            case "movie":
                RMBridgeHttpAsync tvCall = new RMBridgeHttpAsync("http://localhost:7474/?cmd=%7B%22api_id%22:1004,%22command%22:%22send_code%22,%22mac%22:%2234:ea:34:88:f8:61%22,%22data%22:%22260058000001259412131114113812131113121311131213123712371213123711381238113812371213121213121138121313111213111411381237123712131237123811381237120005200001244c12000c470001244c11000d05%22%7D");
                RMBridgeHttpAsync audioCall = new RMBridgeHttpAsync("http://localhost:7474/?cmd=%7B%22api_id%22:1004,%22command%22:%22send_code%22,%22mac%22:%2234:ea:34:88:f8:61%22,%22data%22:%222600a00094941011101110111011103210320f120f120f330f330f330f3310110f120f12101110940f1210110f12101110110f120f120f120f321032103210110f330f330f331032101110110f120f330f00071c939510110f120f120f120f330f3310110f120f330f321032103210110f120f120f120f950f120f120f120f120f1210110f1210110f32103210320f120f330f33103210320f120f120f120f330f000d050000000000000000%22%7D");
                tvCall.execute();
                audioCall.execute();
                break;

            case "ac":
                RMBridgeHttpAsync acCall = new RMBridgeHttpAsync("http://localhost:7474/?cmd=%7B%22api_id%22:1004,%22command%22:%22send_code%22,%22mac%22:%2234:ea:34:88:f8:61%22,%22data%22:%222600b20063833e4040403f2020201f211f211f211e4140403f211e2120201e221e221f2020201f221e211f211f201f221d221f211f201e221f221e413d2261833e413f403e221f20202020211e211f413f403e21202020201e221f20202020211e221e211f2020211e211e21202020211e221e211f40402160823e4140413e221e2020211f211d221f4040413e2020211f211e221e201f2120211e211f211f211f201f211f211f2020211f211d221f413f217f000d05000000000000%22%7D");
                acCall.execute();
        }
    }

}