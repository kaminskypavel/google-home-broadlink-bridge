package com.pavel_kaminsky.googlehome_broadlink_bridge.utils;

import android.util.Log;

import com.stealthcopter.networktools.ARPInfo;
import com.stealthcopter.networktools.SubnetDevices;
import com.stealthcopter.networktools.subnet.Device;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;

public class NetworkUtils {

    public static void scanNetwork() throws Exception {
        SubnetDevices
                .fromLocalAddress()
                .setTimeOutMillis(5000)
                .setNoThreads(10)
                .findDevices(new SubnetDevices.OnSubnetDeviceFound() {
            @Override
            public void onDeviceFound(Device device) {
                Log.d("network onDeviceFound", ToStringBuilder.reflectionToString(device));
                String macAddress = ARPInfo.getMACFromIPAddress(device.ip);
                Log.d("network onDeviceFound", macAddress);
            }

            @Override
            public void onFinished(ArrayList<Device> devicesFound) {
                Log.d("network onFinished", ToStringBuilder.reflectionToString(devicesFound.toArray()));
            }
        });
    }

    public static String MAC2IP(String mac) {
        String ipAddress = ARPInfo.getIPAddressFromMAC(mac);
        return ipAddress;
    }
}
