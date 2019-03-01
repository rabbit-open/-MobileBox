package com.hualala.libcommonui.pageloadview;

import android.view.View;

public interface IPageLoadView {

    void hideAll();

    void setOtherView(View other);

    void setOtherView(int resid);

    void showOtherView();

    void hideOtherView();

    void setEmptyView(int resId);

    void setEmptyView(View empty);

    void showEmpty();

    void hideEmpty();

    void setLoadingView(int resId);

    void setLoadingView(View loadingView);

    void showLoading();

    void hideLoading();

    void setNetworkErrorView(int resId);

    void setNetworkErrorView(View loadingView);

    void showNetworkError();

    void hideNetworkError();

    void setContentView(int resId);

    void setContentView(View content);

    void showContent();

    void hideContent();

    boolean isContentShow();

    boolean isOtherShow();

    boolean isEmptyShow();

    View getVisibleChildView();

}
