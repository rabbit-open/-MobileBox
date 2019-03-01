package com.hualala.libutils.compat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.widget.Toast;
import com.hualala.libutils.MBContext;

public class InstallPackageCompat8 {

    private CallBack callback;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    private static final int REQUEST_CODE_APP_INSTALL = 200;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }

        showToastMessage(context, "版本升级需要允许安装未知来源权限");
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_APP_INSTALL);
    }

    private void showToastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_APP_INSTALL) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = isHasInstallPermissionWithO(MBContext.getContext());
                if (hasInstallPermission) {
                    if (callback != null) {
                        callback.success();
                    }
                } else {
                    if (callback != null) {
                        callback.fail();
                    }
                }
            }
        }
    }


    public void requestPermission(Context context, CallBack callBack) {
        this.callback = callBack;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = isHasInstallPermissionWithO(context);
            if (!hasInstallPermission) {
                startInstallPermissionSettingActivity(context);
                return;
            }
        }
        if (callback != null) {
            callback.success();
        }
    }

    public interface CallBack {
        void success();

        void fail();
    }

}
