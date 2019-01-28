package com.hualala.libcommonui.pageloadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * <b>PageLoadView 页面加载控制View</b>
 * <p>
 * 包括：
 * loading状态、
 * 网络错误状态、
 * 内容展示状态、
 * 空页面展示、
 * 自己定义的OtherView
 * </p>
 */
public class PageLoadView extends FrameLayout implements IPageLoadView {

    protected View mLoading;
    protected View mNetworkError;
    protected View mOther;
    protected View mEmpty;
    protected View mContent;


    public PageLoadView(Context context) {
        this(context, null);
    }

    public PageLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    protected void initView() {
        hideAll();
    }

    public void hideAll() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setVisibility(View.GONE);
        }
    }

    public void setOtherView(View other) {
        if (checkIsExistViewGroup(other)) {
            mOther = other;
            hideView(mOther);
        }
    }

    public void setOtherView(int resid) {
        View view = findViewById(resid);
        setOtherView(view);
    }

    public void showOtherView() {
        hideAll();
        showView(mOther);
    }

    public void hideOtherView() {
        hideView(mOther);
    }

    public void setEmptyView(int resId) {
        View view = findViewById(resId);
        setEmptyView(view);
    }

    public void setEmptyView(View empty) {
        if (checkIsExistViewGroup(empty)) {
            mEmpty = empty;
            hideView(mEmpty);
        }
    }

    public void showEmpty() {
        hideAll();
        showView(mEmpty);
    }

    @Override
    public void hideEmpty() {
        hideView(mEmpty);
    }


    @Override
    public void setNetworkErrorView(int resId) {
        View view = findViewById(resId);
        setNetworkErrorView(view);
    }

    @Override
    public void setNetworkErrorView(View networkErrorView) {
        if (checkIsExistViewGroup(networkErrorView)) {
            mNetworkError = networkErrorView;
            hideView(mNetworkError);
        }
    }

    public void showNetworkError() {
        hideAll();
        showView(mNetworkError);
    }

    public void hideNetworkError() {
        hideView(mNetworkError);
    }

    @Override
    public void setContentView(int resId) {
        View view = findViewById(resId);
        setContentView(view);
    }

    public void setContentView(View content) {
        if (checkIsExistViewGroup(content)) {
            mContent = content;
            hideView(mContent);
        }
    }

    public void hideContent() {
        hideView(mContent);
    }

    public void showContent() {
        hideAll();
        showView(mContent);
    }


    @Override
    public void setLoadingView(int resId) {
        View view = findViewById(resId);
        setLoadingView(view);
    }

    @Override
    public void setLoadingView(View loadingView) {
        if (checkIsExistViewGroup(loadingView)) {
            mLoading = loadingView;
            hideView(mLoading);
        }
    }

    public void showLoading() {
        hideAll();
        showView(mLoading);
    }

    public void hideLoading() {
        hideView(mLoading);
    }

    private static void hideView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    private static void showView(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }


    public boolean isContentShow() {
        return mContent != null && mContent.isShown();
    }

    public boolean isOtherShow() {
        return mOther != null && mOther.isShown();
    }

    public boolean isEmptyShow() {
        return mEmpty != null && mEmpty.isShown();
    }

    public View getVisibleChildView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view != null && view.getVisibility() == View.VISIBLE) {
                return view;
            }
        }
        return null;
    }


    private boolean checkIsExistViewGroup(View view) {
        if (view == null) {
            return false;
        }
        if (view.getParent() != this) {
            removeFromParent(view);
            addView(view);
        }
        return true;
    }

    public static void removeFromParent(View view) {
        if (view == null) {
            return;
        }
        if (view.getParent() == null) {
            return;
        }
        ((ViewGroup) view.getParent()).removeView(view);
    }
}
