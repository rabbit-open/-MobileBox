package com.caidanmao.contract_package.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.caidanmao.contract_package.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new WeiXinAuthApi().getWXAPI(getApplication()).handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq arg0) {
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.getType()) {
            case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                handleShareResult(resp);
                break;
            default:
                break;
        }
        finish();
    }

    private void handleShareResult(BaseResp resp) {
        int errorCode = resp.errCode;
        int resid = errorCode == BaseResp.ErrCode.ERR_OK ? R.string.Weixin_share_success
                : (errorCode == BaseResp.ErrCode.ERR_USER_CANCEL ? R.string.Weixin_share_cancel : R.string.Weixin_share_fail);
        Toast.makeText(this, getString(resid), Toast.LENGTH_SHORT).show();
    }

}
