package com.hualala.libmvp.view;

public interface BaseView extends LoadDataView {

    void showMessage(int titleId, int messageId);

    void showMessage(String title, String message);

}
