package com.hualala.mobilebox.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.hualala.libcommonui.pageloadview.DefaultPageLoadView;
import com.hualala.libcommonui.pageloadview.IPageLoadView;
import com.hualala.mobilebox.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class PullLoadMoreView extends SmartRefreshLayout implements IPageLoadView {

    private DefaultPageLoadView mPageLoadView;


    public PullLoadMoreView(Context context) {
        super(context);
        initView(context);
    }

    public PullLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.common_pull_page_view, this);
        mPageLoadView = findViewById(R.id.pageLoadView);
        if (mPageLoadView != null) {
            mPageLoadView.setNetWorkErrorListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRefreshListener != null) {
                        mRefreshListener.onRefresh(PullLoadMoreView.this);
                    }
                }
            });
        }
        setEnableRefresh(false);
        setEnableLoadmore(false);
    }


    @Override
    public void hideAll() {
        mPageLoadView.hideAll();
    }

    @Override
    public void setOtherView(View other) {
        mPageLoadView.setOtherView(other);
    }

    @Override
    public void setOtherView(int resid) {
        mPageLoadView.setOtherView(resid);
    }

    @Override
    public void showOtherView() {
        setEnableRefresh(false);
        setEnableLoadmore(false);
        mPageLoadView.showOtherView();
    }

    @Override
    public void hideOtherView() {
        mPageLoadView.hideOtherView();
    }

    @Override
    public void setEmptyView(int resId) {
        mPageLoadView.setEmptyView(resId);
    }

    @Override
    public void setEmptyView(View empty) {
        mPageLoadView.setEmptyView(empty);
    }

    @Override
    public void showEmpty() {
        setEnableRefresh(true);
        setEnableLoadmore(false);
        mPageLoadView.showEmpty();
    }

    @Override
    public void hideEmpty() {
        mPageLoadView.hideEmpty();
    }

    @Override
    public void setLoadingView(int resId) {
        mPageLoadView.setLoadingView(resId);
    }

    @Override
    public void setLoadingView(View loadingView) {
        mPageLoadView.setLoadingView(loadingView);
    }

    @Override
    public void showLoading() {
        setEnableRefresh(false);
        setEnableLoadmore(false);
        mPageLoadView.showLoading();
    }

    @Override
    public void hideLoading() {
        mPageLoadView.hideLoading();
    }

    @Override
    public void setNetworkErrorView(int resId) {
        mPageLoadView.setNetworkErrorView(resId);
    }

    @Override
    public void setNetworkErrorView(View loadingView) {
        mPageLoadView.setNetworkErrorView(loadingView);
    }

    @Override
    public void showNetworkError() {
        setEnableRefresh(false);
        setEnableLoadmore(false);
        mPageLoadView.showNetworkError();
    }

    @Override
    public void hideNetworkError() {
        mPageLoadView.hideNetworkError();
    }

    @Override
    public void setContentView(int resId) {
        mPageLoadView.setContentView(resId);
    }

    @Override
    public void setContentView(View content) {
        mPageLoadView.setContentView(content);
    }

    @Override
    public void showContent() {
        setEnableRefresh(true);
        setEnableLoadmore(false);
        mPageLoadView.showContent();
    }

    @Override
    public void hideContent() {
        mPageLoadView.hideContent();
    }

    @Override
    public boolean isContentShow() {
        return mPageLoadView.isContentShow();
    }

    @Override
    public boolean isOtherShow() {
        return mPageLoadView.isOtherShow();
    }

    @Override
    public boolean isEmptyShow() {
        return mPageLoadView.isEmptyShow();
    }

    @Override
    public View getVisibleChildView() {
        return mPageLoadView.getVisibleChildView();
    }

    public void onRefreshCompleted() {
        if (isRefreshing()) {
            finishRefresh();
        }
        if (isLoading()) {
            finishLoadmore();
        }
    }

    public DefaultPageLoadView getPageLoadView() {
        return mPageLoadView;
    }

    public void setOnNetWorkListener(OnClickListener listener) {
        if (mPageLoadView != null) {
            mPageLoadView.setNetWorkErrorListener(listener);
        }
    }

}
