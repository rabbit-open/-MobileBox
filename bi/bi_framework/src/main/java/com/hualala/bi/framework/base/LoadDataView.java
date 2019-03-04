package com.hualala.bi.framework.base;

public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showError(String title, String message);

    void showError(int titleId, int messageId);
}
