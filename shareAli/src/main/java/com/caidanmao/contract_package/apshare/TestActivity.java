package com.caidanmao.contract_package.apshare;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.caidanmao.contract_package.R;

import java.io.File;

public class TestActivity extends Activity {

    AliShareApi shareUtils = new AliShareApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        shareUtils.init(getApplication());

        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareUtils.sendTextMg(getApplicationContext(), "http://www.baidu.com");
            }
        });

        findViewById(R.id.start_zfb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareUtils.sendLocalImage(getApplicationContext(), new File(Environment.getExternalStorageDirectory(), "share.jpg"));
            }
        });
    }

}
