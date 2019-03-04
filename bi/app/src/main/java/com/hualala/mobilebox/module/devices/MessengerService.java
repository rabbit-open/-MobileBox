package com.hualala.mobilebox.module.devices;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;


public class MessengerService extends Service {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };


    private Messenger messenger = new Messenger(handler);

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


}