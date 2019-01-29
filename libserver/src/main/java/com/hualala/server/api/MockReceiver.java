package com.hualala.server.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import com.hualala.server.widget.Utils;


public class MockReceiver extends BroadcastReceiver {

    public static final String MOCK_SERVICE_NETWORK = "mock.crash.network2";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MOCK_SERVICE_NETWORK.equals(intent.getAction())) {
            //开启悬浮窗权限检测
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(context)) {
                    try {
                        Intent targetIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        targetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Utils.showFloatView(intent, context);
        }
    }
}