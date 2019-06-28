package com.caidanmao.contract_package.wxapi;


import android.content.Context;

import com.caidanmao.contract_package.R;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

class WeiXinAuthApi {

    private IWXAPI mWXAPI;
    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    private void registerToWeiXin(Context ctx) {
        mWXAPI = WXAPIFactory.createWXAPI(ctx, KeyAndSecrets.WEIXINAPPID, true);
        mWXAPI.registerApp(KeyAndSecrets.WEIXINAPPID);
    }

    IWXAPI getWXAPI(Context ctx) {
        if (mWXAPI == null) {
            registerToWeiXin(ctx);
        }
        return mWXAPI;
    }

    boolean isWXAPPInstalled(Context ctx) {
        if (getWXAPI(ctx) == null) {
            ToastUtils.showToastMessage(ctx, R.string.Weixin_register_fail);
            return true;
        }
        return !getWXAPI(ctx).isWXAppInstalled();
    }

    boolean isSupportWX(Context ctx) {
        int wxSdkVersion = getWXAPI(ctx).getWXAppSupportAPI();
        return wxSdkVersion < TIMELINE_SUPPORTED_VERSION;
    }
}
