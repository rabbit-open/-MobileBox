package com.hualala.mobilebox.module.test;

import android.view.View;

import com.billy.android.loading.Gloading;
import com.hualala.libcommonui.pageloadview.DefaultPageLoadView;
import com.hualala.ui.widget.PullLoadMoreView;

public class SpecialAdapter implements Gloading.Adapter {

    @Override
    public View getView(final Gloading.Holder holder, View convertView, int status) {
        DefaultPageLoadView loadingStatusView = null;
        //reuse the old view, if possible
        if (convertView != null && convertView instanceof PullLoadMoreView) {
            loadingStatusView = (DefaultPageLoadView) convertView;
        }

        if (loadingStatusView == null) {
            //TODO 绑定特殊view
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
