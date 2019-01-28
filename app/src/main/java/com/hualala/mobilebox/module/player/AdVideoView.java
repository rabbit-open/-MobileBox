package com.hualala.mobilebox.module.player;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class AdVideoView extends FrameLayout {

    private static final String TAG = AdVideoView.class.getSimpleName();

    private VideoView mVideoView;

    private String mVideoPath = null;

    public AdVideoView(Context context) {
        super(context);
        mVideoView = new VideoView(getContext());
        addView(mVideoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public AdVideoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mVideoView = new VideoView(getContext());
        addView(mVideoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public AdVideoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mVideoView = new VideoView(getContext());
        addView(mVideoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void onCreate(Intent intent) {
        mVideoPath = intent.getStringExtra("videoPath");
        Log.d(TAG, "视频播放地址：" + mVideoPath);
        setupVideo();
    }

    private void setupVideo() {
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "video play start");
                mVideoView.start();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "video play complete");
                stopPlaybackVideo();
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "video play error");
                stopPlaybackVideo();
                return true;
            }
        });

        try {
            mVideoView.setVideoURI(Uri.parse(mVideoPath));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "video set video error");
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onDestroy();
    }

    protected void onDestroy() {
        stopPlaybackVideo();
    }

    public void release() {
        stopPlaybackVideo();
    }

    protected void onResume() {
        if (mVideoView != null) {
            if (!mVideoView.isPlaying()) {
                mVideoView.resume();
            }
        }
    }

    public void stop() {
        if (mVideoView != null) {
            Log.d(TAG, "广告播放停止");
            if (mVideoView.canPause()) {
                mVideoView.pause();
            }
        }
    }

    private void stopPlaybackVideo() {
        if (mVideoView != null) {
            try {
                mVideoView.stopPlayback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
