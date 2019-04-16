package com.billy.android.loading;

import android.view.View;

import com.hualala.libcommonui.pageloadview.DefaultPageLoadView;


/**
 * demo to show how to create a {@link Gloading.Adapter}
 *
 * @author billy.qi
 * @see DefaultPageLoadView
 * @since 19/3/18 18:37
 */
public class GlobalAdapter implements Gloading.Adapter {

    @Override
    public View getView(final Gloading.Holder holder, View convertView, int status) {
        DefaultPageLoadView loadingStatusView = null;
        //reuse the old view, if possible
        if (convertView != null && convertView instanceof DefaultPageLoadView) {
            loadingStatusView = (DefaultPageLoadView) convertView;
        }

        if (loadingStatusView == null) {
            loadingStatusView = new DefaultPageLoadView(holder.getContext());
            loadingStatusView.setNetWorkErrorListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.getRetryTask().run();
                }
            });
        }

        if (status == Gloading.STATUS_EMPTY_DATA) {
            loadingStatusView.showEmpty();
        }
        if (status == Gloading.STATUS_LOAD_FAILED) {
            //TODO wifi是否连接判断,显示网络错误还是错误码
            loadingStatusView.showNetworkError();
        }
        if (status == Gloading.STATUS_LOADING) {
            loadingStatusView.showLoading();
        }
        loadingStatusView.setVisibility(status == Gloading.STATUS_LOAD_SUCCESS ? View.GONE : View.VISIBLE);

        return loadingStatusView;
    }

}