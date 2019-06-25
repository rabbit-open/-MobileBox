package com.hualala.mobilebox.module.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.PatternMatcher;
import android.support.annotation.Nullable;

import com.billy.android.loading.BaseActivity;
import com.hualala.mobilebox.R;

import java.util.regex.Pattern;

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

    public static void main(String[] args) {
        String test = "Z001";
        boolean bool = Pattern.compile("[\u4E00-\u9FFF]+").matcher(test).lookingAt();
        System.out.print(bool);
    }
}
