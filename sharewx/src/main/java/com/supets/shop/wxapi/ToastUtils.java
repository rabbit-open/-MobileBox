package com.supets.shop.wxapi;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

class ToastUtils {

    static void showToastMessage(Context ctx, int resId) {
        if (resId == 0) {
            return;
        }
        showToastMessage(ctx, ctx.getString(resId));
    }

    private static void showToastMessage(Context ctx, String toastString) {
        if (TextUtils.isEmpty(toastString)) {
            return;
        }

        Toast toast = Toast.makeText(ctx, toastString, Toast.LENGTH_SHORT);
        toast.show();
    }

    static void showToastMessageCENTER(Context ctx, String toastString) {
        if (TextUtils.isEmpty(toastString)) {
            return;
        }
        Toast toast = Toast.makeText(ctx, toastString,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
