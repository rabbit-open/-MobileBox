package com.hualala.server.api;

import android.content.Context;
import android.content.SharedPreferences;

public class ServerConfigPref {

    private static final String ServerConfigPref = "ServerConfigPref";

    private static SharedPreferences.Editor getEditor(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences getSharedPreferences(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static void saveData(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getEditor(context, ServerConfigPref);
        editor.putBoolean(key, value);
        editor.commit();
    }

    private static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context, ServerConfigPref);
        return sp.getBoolean(key, false);
    }

    private static void saveData(Context context, String key, String value) {
        SharedPreferences.Editor editor = getEditor(context, ServerConfigPref);
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context, ServerConfigPref);
        return sp.getString(key, "");
    }

    private static void removeData(Context context, String key) {
        SharedPreferences.Editor editor = getEditor(context, ServerConfigPref);
        editor.remove(key);
        editor.commit();
    }


    public static void setFloatWindowSwitchStatus(Context context, boolean isOpen) {
        saveData(context, "floatSwitch", isOpen);
    }

    public static boolean getFloatWindowSwitchStatus(Context context) {
        return getBoolean(context, "floatSwitch");
    }
}
