package com.hualala.mobilebox.module.player;

import android.os.Bundle;

import com.google.gson.Gson;
import com.hualala.bi.framework.base.BaseContractorActivity;
import com.hualala.mobilebox.R;
import com.hualala.server.event.LRCEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Mp3LRCActivity extends BaseContractorActivity {

    private LedTextView2 songScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3player3);

        songScreen = findViewById(R.id.songScreen);
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void lrcCallBack(LRCEvent lrcEvent) {
        LRCContent lrcContent = new Gson().fromJson(lrcEvent.content, LRCContent.class);
        songScreen.stopScroll();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        songScreen.ForceupdateText(lrcContent.content);
        if (lrcContent.isScroll) {
            songScreen.startScroll();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}