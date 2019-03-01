package com.hualala.libutils.compat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

public class OverLayWindowCompact {

    public static boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                try {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
            return true;
        }

        return false;

    }

}
