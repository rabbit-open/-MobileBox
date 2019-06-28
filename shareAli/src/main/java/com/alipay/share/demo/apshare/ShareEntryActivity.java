package com.alipay.share.demo.apshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alipay.share.demo.R;
import com.alipay.share.sdk.openapi.BaseReq;
import com.alipay.share.sdk.openapi.BaseResp;
import com.alipay.share.sdk.openapi.IAPAPIEventHandler;

public class ShareEntryActivity extends Activity implements IAPAPIEventHandler {
    private AliShareApi api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = new AliShareApi();
        api.init(getApplication());
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int result;

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.Ali_errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.Ali_errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.Ali_;
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                result = R.string.Ali_errcode_send_fail;
                break;
            default:
                result = R.string.Ali_errcode_unknown;
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        finish();
    }
}
