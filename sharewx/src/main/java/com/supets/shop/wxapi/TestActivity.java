package com.supets.shop.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WeXinShareApi.shareLocalImageToSNS(getApplication(),
                new File(Environment.getExternalStorageDirectory(), "share.jpg").getAbsolutePath(),
                "", false);

        WeXinShareApi.shareTextToSNS(getApplication(), "http://www.baidu.com", false);
    }

}
