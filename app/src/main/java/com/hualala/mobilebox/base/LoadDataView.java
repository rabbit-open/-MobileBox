package com.hualala.mobilebox.base;

public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showError(String title, String message);

    void showError(int titleId, int messageId);
}
