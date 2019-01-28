package com.hualala.mobilebox.base;

import com.google.gson.JsonSyntaxException;
import com.hualala.mobilebox.R;
import retrofit2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ErrorUtils {

    public static void handleError(LoadDataView view, Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof ConnectException || throwable instanceof UnknownHostException) {
            view.showError(R.string.m_base_network_error, R.string.m_base_can_not_connect_server);
        } else if (throwable instanceof SocketTimeoutException) {
            view.showError(R.string.m_base_network_error, R.string.m_base_connect_timeout);
        } else if (throwable instanceof JsonSyntaxException) {
            view.showError(R.string.m_base_data_error, R.string.m_base_data_error_on_json_paser);
        } else if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            if (exception.code() == 422) {
                view.showError(R.string.m_base_data_error, R.string.m_base_data_submit_error);
            } else {
                view.showError(R.string.m_base_network_error, R.string.m_base_access_server_error);
            }
        } else {
            view.showError("操作失败", throwable.getMessage());
        }
    }
}
