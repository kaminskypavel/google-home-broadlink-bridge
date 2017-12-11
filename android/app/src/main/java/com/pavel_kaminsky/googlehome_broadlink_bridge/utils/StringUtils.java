package com.pavel_kaminsky.googlehome_broadlink_bridge.utils;

import java.util.regex.Pattern;

/**
 * Created by PK on 12/11/2017.
 */

public class StringUtils {

    public static boolean validateMAC(String mac) {
        Pattern p = Pattern.compile("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
        return p.matcher(mac).find();
    }

    public static boolean validateIP(final String ip) {
        Pattern p = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        return p.matcher(ip).matches();
    }

}
