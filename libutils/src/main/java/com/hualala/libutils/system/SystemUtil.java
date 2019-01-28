package com.hualala.libutils.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.hualala.libutils.MBContext;
import com.hualala.libutils.wifi.NetworkUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.TimeZone;

public class SystemUtil {

    private static String sDeviceId;

    public static boolean checkPermission(String permission) {
        return MBContext.getContext().getPackageManager().checkPermission(permission, MBContext.getContext().getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }

    public static String getNetworkType() {
        return NetworkUtil.getNetworkConnection(MBContext.getContext()).toString();
    }

    public static String getNetworkSubType() {
        return NetworkUtil.getNetworkSubType(MBContext.getContext());
    }

    public static String getResolution() {
        DisplayMetrics metrics = MBContext.getContext().getResources().getDisplayMetrics();
        if (metrics == null) {
            return null;
        }
        return metrics.widthPixels + "*" + metrics.heightPixels;
    }

    public static String getOsVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getAppVersion() {
        try {
            PackageInfo packageInfo = MBContext.getContext().getPackageManager().getPackageInfo(MBContext.getContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    public static String getOperator() {
        TelephonyManager teleManager = (TelephonyManager) MBContext.getContext().getSystemService(Context.TELEPHONY_SERVICE);

        String operator = teleManager.getNetworkOperatorName();
        if (operator == null) {
            operator = teleManager.getSimOperatorName();
        }
        return operator;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getDeviceId() {
        if (sDeviceId != null) {
            return sDeviceId;
        }
        Context context = MBContext.getContext();
        if (context == null) {
            return null;
        }

        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (imei == null) {
            imei = "";
        }
        String macAddress = NetworkUtil.getLocalMacAddress(context);
        if (macAddress == null) {
            macAddress = "";
        }

        sDeviceId = md5(imei + macAddress);

        return sDeviceId;
    }

    public static String getMetaData(String name) {
        try {
            ApplicationInfo applicationInfo;
            applicationInfo = MBContext.getContext().getPackageManager().getApplicationInfo(MBContext.getContext().getPackageName(),
                    PackageManager.GET_META_DATA);
            return applicationInfo.metaData.getString(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCpuName() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/proc/cpuinfo"));
            String text = br.readLine();
            br.close();

            String[] array = text.split(":\\s+", 2);
            return array[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getCountry() {
        return Locale.getDefault().getCountry();
    }

    public static String getTimezone() {
        return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes("UTF-8"));
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                if ((b & 0xFF) < 0x10)
                    sb.append("0");
                sb.append(Integer.toHexString(b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            return input;
        }
    }

    public static String getAndroidId() {
        try {
            return Settings.Secure.getString(MBContext.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }

}
