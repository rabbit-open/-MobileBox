package com.hualala.mobilebox.module.guide;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.hualala.bi.framework.base.BaseContractorActivity;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.module.UINavgation;
import com.unistrong.yang.zb_permission.ZbPermission;

public class AppStartActivity extends BaseContractorActivity implements Handler.Callback {
    public static final int DEFAULT_WHAT = 0;
    public static final int DEFAULT_TIME = 2000;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);
        initPermission();
    }

    private void initPermission() {
        ZbPermission.needPermission(this, 16, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
        }, new ZbPermission.ZbPermissionCallback() {
            @Override
            public void permissionSuccess(int i) {
                initView();
            }

            @Override
            public void permissionFail(int i) {
                showTip();
            }
        });
    }


    private void showTip() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setMessage("权限提示");
        dialog.setMessage("程序需要使用以下权限才能正常使用:\n1 需要联网权限" +
                "\n2 需要读写SD卡权限" +
                "\n3 需要拍照权限");
        dialog.setNegativeButton("不同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.setPositiveButton("同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoSet();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void gotoSet() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivityForResult(intent, 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            initPermission();
        }
    }

    void initView() {
        mHandler = new Handler(this);
        mHandler.sendEmptyMessageDelayed(DEFAULT_WHAT, DEFAULT_TIME);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (isRemove) {
            finish();
        } else {
            if (msg.what == DEFAULT_WHAT) {
                UINavgation.startMainActivity(this);
                finish();
            }
        }
        return true;
    }

    private boolean isRemove = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mHandler != null) {
            mHandler.removeMessages(DEFAULT_WHAT);
            isRemove = true;
        }
    }

}
