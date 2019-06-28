package com.caidanmao.contract_package.apshare;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.alipay.share.sdk.openapi.APAPIFactory;
import com.alipay.share.sdk.openapi.APImageObject;
import com.alipay.share.sdk.openapi.APMediaMessage;
import com.alipay.share.sdk.openapi.APTextObject;
import com.alipay.share.sdk.openapi.IAPAPIEventHandler;
import com.alipay.share.sdk.openapi.IAPApi;
import com.alipay.share.sdk.openapi.SendMessageToZFB;
import com.caidanmao.contract_package.R;

import java.io.File;


public class AliShareApi {

    private IAPApi api;

    public void init(Application application) {
        api = APAPIFactory.createZFBApi(application, Constants.APP_ID, false);
    }

    public void handleIntent(Intent intent, IAPAPIEventHandler handler) {
        api.handleIntent(intent, handler);
    }

    public void sendTextMg(Context context, String text) {

        if (!api.isZFBAppInstalled()) {
            Toast.makeText(context, context.getString(R.string.Ali_not_install_notify), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!api.isZFBSupportAPI()) {
            Toast.makeText(context, context.getString(R.string.Ali_not_support_API), Toast.LENGTH_SHORT).show();
            return;
        }

        //初始化一个APTextObject对象
        APTextObject textObject = new APTextObject();
        textObject.text = text;

        //用APTextObject对象初始化一个APMediaMessage对象
        APMediaMessage mediaMessage = new APMediaMessage();
        mediaMessage.mediaObject = textObject;

        //构造一个Req
        SendMessageToZFB.Req req = new SendMessageToZFB.Req();
        req.message = mediaMessage;

        //调用api接口发送消息到支付宝
        api.sendReq(req);
    }

    public void sendLocalImage(Context context, File file) {

        if (!api.isZFBAppInstalled()) {
            Toast.makeText(context, context.getString(R.string.Ali_not_install_notify), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!api.isZFBSupportAPI()) {
            Toast.makeText(context, context.getString(R.string.Ali_not_support_API), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!file.exists()) {
            return;
        }

        APImageObject imageObject = new APImageObject();
        imageObject.imagePath = file.getAbsolutePath();
        APMediaMessage mediaMessage = new APMediaMessage();
        mediaMessage.mediaObject = imageObject;
        SendMessageToZFB.Req req = new SendMessageToZFB.Req();
        req.message = mediaMessage;
        req.transaction = buildTransaction("image");
        api.sendReq(req);
    }

    private String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


}
