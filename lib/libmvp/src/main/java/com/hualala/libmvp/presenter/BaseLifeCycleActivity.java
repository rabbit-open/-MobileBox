package com.hualala.libmvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.hualala.libmvp.view.BaseView;

public abstract class BaseLifeCycleActivity extends AppCompatActivity implements BaseView {

    protected String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public void showMessage(int titleId, int messageId) {
    }

    @Override
    public void showMessage(String title, String message) {
    }

    @Override
    public void showError(int titleId, int messageId) {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
