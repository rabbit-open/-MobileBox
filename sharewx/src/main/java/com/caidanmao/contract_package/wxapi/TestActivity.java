package com.caidanmao.contract_package.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.caidanmao.contract_package.R;
import com.unistrong.yang.zb_permission.Permission;
import com.unistrong.yang.zb_permission.ZbPermission;

import java.io.File;

public class TestActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.sharePic).setOnClickListener(this);
        findViewById(R.id.shareText).setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        ZbPermission.needPermission(this, 300, Permission.STORAGE, new ZbPermission.ZbPermissionCallback() {
            @Override
            public void permissionSuccess(int requsetCode) {

                if (v.getId() == R.id.sharePic) {
                    WeXinShareApi.shareLocalImageToSNS(getApplication(),
                            new File(Environment.getExternalStorageDirectory(), "share.jpg").getAbsolutePath(),
                            "", false);
                } else if (v.getId() == R.id.shareText) {
                    WeXinShareApi.shareTextToSNS(getApplication(), "http://www.baidu.com", false);
                }
            }

            @Override
            public void permissionFail(int requestCode) {

            }
        });

    }
}
