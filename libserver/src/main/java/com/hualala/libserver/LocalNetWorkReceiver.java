package com.hualala.libserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class LocalNetWorkReceiver extends BroadcastReceiver {
    public static final String MOCK_SERVICE_NETWORK = "mock.crash.network2";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MOCK_SERVICE_NETWORK.equals(intent.getAction())) {
            //开启悬浮窗权限检测


        }
    }
}