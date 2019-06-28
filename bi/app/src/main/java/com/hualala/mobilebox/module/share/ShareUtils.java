package com.hualala.mobilebox.module.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.hualala.libutils.compat.FileProviderCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShareUtils {

    public static void shareImage(Context context, File targetFile) {
        Uri imageUri = FileProviderCompat.getFileUri(context, targetFile);
        Intent shareIntent = new Intent();
        shareIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");//微信朋友
//        shareIntent.setClassName("com.tencent.mobileqq", "cooperation.qqfav.widget.QfavJumpActivity");//保存到QQ收藏
//        shareIntent.setClassName("com.tencent.mobileqq", "cooperation.qlink.QlinkShareJumpActivity");//QQ面对面快传
//        shareIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.qfileJumpActivity");//传给我的电脑
//        shareIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");//QQ好友或QQ群
//        shareIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");//微信朋友圈，仅支持分享图片
     //   shareIntent.setClassName("com.eg.android.AlipayGphone", "com.alipay.mobile.quinox.SchemeLauncherActivity");//微信朋友圈，仅支持分享图片

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        context.startActivity(Intent.createChooser(shareIntent, "分享到"));

    }

    public static void shareText(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "标题");
        intent.putExtra(Intent.EXTRA_TEXT, "描述信息" + "这里你可以追加一个url连接");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    public static void shareImageText(Context context, File targetFile, String text) {
        if (targetFile == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", FileProviderCompat.getFileUri(context, targetFile));
        intent.putExtra("android.intent.extra.TEXT", text);
        context.startActivity(intent);
    }

    public static File saveFile(Bitmap bmp) {

        File currentFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (currentFile.exists()) {
            return currentFile;
        }
        return null;
    }


    public static Bitmap getBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        // Draw background
        Drawable bgDrawable = v.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(c);
        else
            c.drawColor(Color.WHITE);
        // Draw view to canvas
        v.draw(c);
        return b;
    }

}
