package com.pavel_kaminsky.googlehome_broadlink_bridge.utils;

import com.stealthcopter.networktools.ARPInfo;
import com.stealthcopter.networktools.SubnetDevices;
import com.stealthcopter.networktools.subnet.Device;

import java.util.ArrayList;
import java.util.function.Consumer;

public class NetworkUtils {

    public static void scanNetwork(Consumer<ArrayList<Device>> function) throws Exception {
        SubnetDevices
                .fromLocalAddress()
                .setTimeOutMillis(5000)
                .setNoThreads(10)
                .findDevices(new SubnetDevices.OnSubnetDeviceFound() {

            @Override
            public void onDeviceFound(Device device) {
            }

            @Override
            public void onFinished(ArrayList<Device> devicesFound) {
//                Log.d("network onFinished", ToStringBuilder.reflectionToString(devicesFound.toArray()));
                function.accept(devicesFound);
            }
        });
    }

    public static String MAC2IP(String mac) {
        String ipAddress = ARPInfo.getIPAddressFromMAC(mac);
        return ipAddress;
    }
}
