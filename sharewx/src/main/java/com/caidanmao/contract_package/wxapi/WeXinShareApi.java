package com.caidanmao.contract_package.wxapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.caidanmao.contract_package.R;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;

import java.io.File;


public class WeXinShareApi {

    private static final int THUMB_SIZE = 150;
    private static final int MAX_WEIXIN_PHOTO_SIZE = 32 * 1024; // 32KB

    public static void shareLocalImageToSNS(Context ctx, String path,
                                            String description,
                                            boolean isfriends) {

        WeiXinAuthApi api = new WeiXinAuthApi();

        if (api.isWXAPPInstalled(ctx)) {
            ToastUtils.showToastMessage(ctx, R.string.Weixin_not_install_notify);
            return;
        }

        if (api.isSupportWX(ctx)) {
            ToastUtils.showToastMessage(ctx, R.string.Weixin_not_support_API);
            return;
        }

        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        WXImageObject imgObj = new WXImageObject();
        imgObj.setImagePath(path);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        msg.description = description;

        Bitmap bmp = BitmapFactory.decodeFile(path);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE,
                THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = ImageUtils.Bitmap2Bytes(thumbBmp,
                MAX_WEIXIN_PHOTO_SIZE);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("image");
        req.message = msg;
        if (isfriends) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }
        api.getWXAPI(ctx).sendReq(req);
    }

    public static void shareTextToSNS(Context ctx, String text, boolean isfriends) {

        WeiXinAuthApi api = new WeiXinAuthApi();

        if (api.isWXAPPInstalled(ctx)) {
            ToastUtils.showToastMessage(ctx, R.string.Weixin_not_install_notify);
            return;
        }

        if (api.isSupportWX(ctx)) {
            ToastUtils.showToastMessage(ctx, R.string.Weixin_not_support_API);
            return;
        }

        // 初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;   // 发送文本类型的消息时，title字段不起作用
        // msg.title = "Will be ignored";
        msg.description = text;   // 构造一个Req

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;   // 分享或收藏的目标场景，通过修改scene场景值实现。
        // 发送到聊天界面 —— WXSceneSession
        // 发送到朋友圈 —— WXSceneTimeline
        // 添加到微信收藏 —— WXSceneFavorite
        if (isfriends) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        // 调用api接口发送数据到微信
        api.getWXAPI(ctx).sendReq(req);
    }

    private static String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}
