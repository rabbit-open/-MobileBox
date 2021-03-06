package com.hualala.server.api;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.hualala.libserver.R;

import java.net.InetSocketAddress;

public class MBService extends Service {

    private MockReceiver mockDataReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mockDataReceiver = new MockReceiver();
        registerReceiver(new MockReceiver(), new IntentFilter(MockReceiver.MOCK_SERVICE_NETWORK));
        if (!NotificationManagerCompat.from(getApplicationContext()).areNotificationsEnabled()) {
            NotificationUtils.goToSet(getApplicationContext());
        }
        //启动后台服务
        ServerApi.getInstance().setContext(getApplication());
        //启动设备监听服务
        new DeviceWaitingSearch(getApplication(),Build.MODEL,Build.BRAND){

            @Override
            public void onDeviceSearched(InetSocketAddress socketAddr) {
                Log.v("test", "已上线，搜索主机：" + socketAddr.getAddress().getHostAddress() + ":" + socketAddr.getPort());
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= 26) {
            startForeground(1, new NotificationUtils(getApplicationContext(),
                    "channel_mock", getString(R.string.notify_channel_id), getString(R.string.notify_channel_name))
                    .createNotification(getString(R.string.notify_channel_title), getString(R.string.notify_channel_content)));
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mockDataReceiver);
        if (Build.VERSION.SDK_INT >= 26) {
            stopForeground(true);
        }
    }
}