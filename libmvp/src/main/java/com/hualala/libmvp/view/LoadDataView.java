package com.hualala.libmvp.view;

public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showError(String title, String message);

    void showError(int titleId, int messageId);
}
