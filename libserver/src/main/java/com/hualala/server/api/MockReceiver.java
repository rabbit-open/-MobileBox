package com.hualala.server.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hualala.server.widget.Utils;


public class MockReceiver extends BroadcastReceiver {

    public static final String MOCK_SERVICE_NETWORK = "mock.crash.network2";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MOCK_SERVICE_NETWORK.equals(intent.getAction())) {
            Utils.showFloatView(intent);
        }
    }
}