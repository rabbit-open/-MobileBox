//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hualala.libutils.wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetworkUtil {
    public NetworkUtil() {
    }

    public static String getLocalMacAddress(Context context) {
        WifiManager wifi = (WifiManager)context.getSystemService("wifi");
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    public static NetworkUtil.NetworkConnection getNetworkConnection(Context context) {
        if (context == null) {
            return NetworkUtil.NetworkConnection.NOT_CONNECTED;
        } else {
            ConnectivityManager connect = (ConnectivityManager)context.getSystemService("connectivity");
            NetworkInfo wifi = connect.getNetworkInfo(1);
            NetworkInfo mobile = connect.getNetworkInfo(0);
            if (wifi != null && wifi.isConnected()) {
                return NetworkUtil.NetworkConnection.WIFI_CONNECTED;
            } else {
                return mobile != null && mobile.isConnected() ? getNetworkConnectoinBySubtype(mobile.getSubtype()) : NetworkUtil.NetworkConnection.NOT_CONNECTED;
            }
        }
    }

    private static NetworkUtil.NetworkConnection getNetworkConnectoinBySubtype(int subtype) {
        switch(subtype) {
        case 1:
        case 2:
        case 4:
        case 7:
        case 11:
            return NetworkUtil.NetworkConnection.MOBILE_2G_CONNECTED;
        case 3:
        case 5:
        case 6:
        case 8:
        case 9:
        case 10:
        case 12:
        case 14:
        case 15:
            return NetworkUtil.NetworkConnection.MOBILE_3G_CONNECTED;
        case 13:
            return NetworkUtil.NetworkConnection.MOBILE_4G_CONNECTED;
        default:
            return NetworkUtil.NetworkConnection.NOT_CONNECTED;
        }
    }

    public static String getNetworkSubType(Context context) {
        String subType = "Unknown";
        if (context == null) {
            return subType;
        } else {
            ConnectivityManager manager = (ConnectivityManager)context.getSystemService("connectivity");
            if (manager == null) {
                return subType;
            } else {
                NetworkInfo networkInfo = manager.getNetworkInfo(0);
                if (networkInfo != null && networkInfo.getState() == State.CONNECTED) {
                    subType = networkInfo.getSubtypeName();
                }

                return subType;
            }
        }
    }

    public static boolean isNetCircuitBreaker(Context context) {
        return getNetworkConnection(context) == NetworkUtil.NetworkConnection.NOT_CONNECTED;
    }

    public static enum NetworkConnection {
        NOT_CONNECTED,
        WIFI_CONNECTED,
        MOBILE_2G_CONNECTED,
        MOBILE_3G_CONNECTED,
        MOBILE_4G_CONNECTED;

        private NetworkConnection() {
        }

        public String toString() {
            switch(this) {
            case NOT_CONNECTED:
                return null;
            case WIFI_CONNECTED:
                return "wifi";
            case MOBILE_2G_CONNECTED:
                return "2G";
            case MOBILE_3G_CONNECTED:
                return "3G";
            case MOBILE_4G_CONNECTED:
                return "4G";
            default:
                return null;
            }
        }
    }
}
