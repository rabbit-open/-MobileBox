package com.hualala.mobilebox.module.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.billy.android.loading.BaseActivity;
import com.billy.android.loading.Gloading;
import com.hualala.mobilebox.R;
import com.hualala.ui.widget.PullLoadMoreView;

public class TestLay2 extends BaseActivity {


    Handler handler = new Handler();

    private PullLoadMoreView pullLoadMoreView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pullLoadMoreView = new PullLoadMoreView(this);
        View view = View.inflate(this, R.layout.activity_app_start, null);
        pullLoadMoreView.setContentView(view);
        setContentView(pullLoadMoreView);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoadSuccess();
                pullLoadMoreView.showEmpty();
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
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.from(new SpecialAdapter()).wrap(this).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    @Override
    protected void onLoadRetry() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoadSuccess();
                pullLoadMoreView.showContent();
            }
        }, 6000);
    }
}
