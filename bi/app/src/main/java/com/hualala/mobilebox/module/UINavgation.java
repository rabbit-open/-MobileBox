package com.hualala.mobilebox.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.hualala.mobilebox.module.boot.view.MainActivity;
import com.hualala.mobilebox.module.devices.DevicesListActivity;
import com.hualala.mobilebox.module.player.Mp3PlayerActivity;
import com.hualala.mobilebox.module.player.VideoPlayerActivity;
import com.hualala.mobilebox.module.zxing.CodeScanActivity;

public class UINavgation {

    public static void startPlayerActivity(Context context, String path) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra("videoPath", path);
        intent.putExtra("liveStreaming", 0);
        intent.putExtra("cache", true);
        intent.putExtra("loop", false);
        intent.putExtra("video-data-callback", false);
        intent.putExtra("audio-data-callback", false);
        intent.putExtra("disable-log", true);
        intent.putExtra("start-pos", 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startMp3PlayerActivity(Context context, String path) {
        Intent intent = new Intent(context, Mp3PlayerActivity.class);
        intent.putExtra("path", path);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static final int ScanCode = 0xff;

    public static void startScanCodeActivity(Activity context) {
        Intent intent = new Intent(context, CodeScanActivity.class);
        context.startActivityForResult(intent, ScanCode);
    }

    public static void startDevicesListActivity(Context context) {
        Intent intent = new Intent(context, DevicesListActivity.class);
        ((Activity) context).startActivityForResult(intent, ScanCode);
    }

    public static void startScanCodeActivity(Fragment context) {
        Intent intent = new Intent(context.getActivity(), CodeScanActivity.class);
        context.startActivityForResult(intent, ScanCode);
    }

    public static void startLivePlayerActivity(Context context, String path) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra("videoPath", path);
        intent.putExtra("liveStreaming", 1);
        intent.putExtra("cache", true);
        intent.putExtra("loop", true);
        intent.putExtra("video-data-callback", false);
        intent.putExtra("audio-data-callback", false);
        intent.putExtra("disable-log", true);
        intent.putExtra("start-pos", 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
