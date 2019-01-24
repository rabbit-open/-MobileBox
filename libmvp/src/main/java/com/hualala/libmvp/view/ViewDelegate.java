package com.hualala.libmvp.view;

import android.support.annotation.Keep;
import android.view.View;


@Keep
public class ViewDelegate<T extends View> implements BaseView {

    private T mView;

    public ViewDelegate(T view) {
        this.mView = view;
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
