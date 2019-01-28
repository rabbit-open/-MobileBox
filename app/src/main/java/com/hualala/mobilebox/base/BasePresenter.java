package com.hualala.mobilebox.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.CallSuper;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T> implements Presenter, LifecycleObserver {

    protected T mView;

    public void setView(T view) {
        mView = view;
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
    }

    @Override
    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        mView = null;
    }

    protected T getView() {
        return mView;
    }

    protected void dispose(Disposable useCase) {
        if (useCase != null) {
            useCase.dispose();
        }
    }
}
