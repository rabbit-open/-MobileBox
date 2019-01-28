package com.hualala.libcommonui.pageloadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.hualala.libcommonui.R;

public class DefaultPageLoadView extends PageLoadView implements View.OnClickListener {


    private TextView mEmptyText;

    private View mRefresh;

    public DefaultPageLoadView(Context context) {
        super(context);
    }

    public DefaultPageLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void initView() {

        LayoutInflater.from(getContext()).inflate(R.layout.common_page_view, this);

        mLoading = findViewById(R.id.page_view_loading);

        mEmpty = findViewById(R.id.page_view_empty);
        mEmptyText = findViewById(R.id.page_view_empty_text);

        mNetworkError = findViewById(R.id.page_view_network_error);

        mRefresh = findViewById(R.id.page_view_refresh);
        mRefresh.setOnClickListener(this);

        hideAll();
    }


    @Override
    public void onClick(View v) {
        if (mNetWorkErrorListener != null) {
            showLoading();
            mNetWorkErrorListener.onClick(v);
        }
    }

    public OnClickListener mNetWorkErrorListener;

    public void setNetWorkErrorListener(OnClickListener netWorkErrorListener) {
        this.mNetWorkErrorListener = netWorkErrorListener;
    }

    public void setEmptyText(String text) {
        if (mEmptyText != null) {
            mEmptyText.setText(text);
        }
    }

    public void setEmptyText(int resid) {
        if (mEmptyText != null) {
            mEmptyText.setText(resid);
        }
    }

}
