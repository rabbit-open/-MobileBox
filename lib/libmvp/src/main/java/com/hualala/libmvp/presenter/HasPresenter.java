package com.hualala.libmvp.presenter;

public interface HasPresenter<T extends Presenter> {
    T getPresenter();
}
