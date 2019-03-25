package com.hualala.mobilebox.module.wifi;

import android.content.Context;
import android.content.SharedPreferences;

public class WifiConfig {

    private static SharedPreferences mWifiSharedPreferences;

    public static void init(Context context) {
        mWifiSharedPreferences = context.getSharedPreferences("wifiConfig", Context.MODE_PRIVATE);
    }

    public static void putSSID(String ssid, String pass) {
        mWifiSharedPreferences.edit().putString(ssid, pass).apply();
    }

    public static String getSSID(String ssid) {
        return mWifiSharedPreferences.getString(ssid, "");
    }

    public static void clear() {
        mWifiSharedPreferences.edit().clear().apply();
    }

}
