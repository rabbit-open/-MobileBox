package com.hualala.libmvp.presenter;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.hualala.libmvp.view.BaseView;

public abstract class BaseRxActivity extends AppCompatActivity implements BaseView {

    protected String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    private static Presenter sDummyPresenter = new Presenter() {
        @Override
        public void resume() {
        }

        @Override
        public void pause() {
        }

        @Override
        public void destroy() {
        }
    };

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        getOptionalPresenter().resume();
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        getOptionalPresenter().pause();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        getOptionalPresenter().destroy();
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

    private Presenter getOptionalPresenter() {
        Presenter presenter = sDummyPresenter;
        if (this instanceof HasPresenter && ((HasPresenter) this).getPresenter() != null) {
            presenter = ((HasPresenter) this).getPresenter();
        }
        return presenter;
    }

}
