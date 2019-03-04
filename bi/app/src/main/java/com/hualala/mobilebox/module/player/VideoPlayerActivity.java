package com.hualala.mobilebox.module.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.base.BaseContractorActivity;
import com.hualala.ui.widget.AdMediaPlayerView;

public class VideoPlayerActivity extends BaseContractorActivity {


    AdMediaPlayerView mediaPlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);
        mediaPlayerView = findViewById(R.id.player);
        mediaPlayerView.stop();
        mediaPlayerView.onCreate(getIntent());
    }
}
