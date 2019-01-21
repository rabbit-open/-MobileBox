//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.hualala.mobilebox.lib.context;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class MBContext {

    private static Application mContext;

    public static void initContext(Application context) {
        MBContext.mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void toast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(int msgId) {
        Toast.makeText(mContext, msgId, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
