package com.hualala.mobilebox.module.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.billy.android.loading.BaseActivity;
import com.hualala.mobilebox.R;

public class TestLay extends BaseActivity {


    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showEmpty();
            }
        }, 2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoadFailed();
            }
        }, 4000);

        showLoading();
    }

    @Override
    protected void onLoadRetry() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoadSuccess();
            }
        }, 6000);
    }
}
