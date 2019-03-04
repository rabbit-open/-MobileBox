package com.hualala.bi.framework.base;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;


@Keep
public class ViewDelegate<T extends View> implements BaseView {

    private T mView;

    public ViewDelegate(T view) {
        this.mView = view;
    }

    public Context getContext() {
        return mView.getContext();
    }

    public FragmentActivity getFragmentActivity() {
        if (mView instanceof ViewGroup){
            return (FragmentActivity) mView.getContext();
        }
        return (FragmentActivity) mView.getContext();
    }

    public T getView() {
        return mView;
    }

    @Override
    public void showMessage(int titleId, int messageId) {

    }

    @Override
    public void showMessage(String title, String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void showError(int titleId, int messageId) {

    }
}
