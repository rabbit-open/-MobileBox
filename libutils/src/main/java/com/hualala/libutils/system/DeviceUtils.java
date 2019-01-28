package com.hualala.libutils.system;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.WIFI_SERVICE;

/**
 * Android 设备工具DeviceUtils
 * 常用方法：dp转换px px转换dp 设备宽度 设备高度 SD卡判断 网络判断
 * app:VersionName app:VersionCode DeviceId sn号 硬件品牌 系统品牌 手机型号 手机imei号 系统Android API等级 系统Android 版本
 * Uri转换File 获取AndroidManifestxml里meta data的值 sd判断，网络判断
 */
public class DeviceUtils {
    /**
     * 获取硬件制造商
     *
     * @return
     */
    public static String getPhoneManufacturer() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER;
    }

    /**
     * 获取硬件系列号
     *
     * @return
     */
    public static String getPhoneSerial(Context context) {
        String serial = "";
        //        return "0123456789ABCDEF";
        return (serial = Build.SERIAL) == null ? "" : serial;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL == null ? "" : Build.MODEL;
    }

    /**
     * 获取手机系统定制商
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND == null ? "" : Build.BRAND;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static String getBuildSdkInt() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildSdkVersion() {
        return Build.VERSION.RELEASE == null ? "" : Build.VERSION.RELEASE;
    }

    /**
     * 获取设备的唯一标识，deviceId
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String deviceId = "";
        if (context != null) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    deviceId = tm.getDeviceId();
                }
            }
        }
        return deviceId == null ? "" : deviceId;
    }

    /**
     * 获取设备的唯一标识，imei
     *
     * @param context
     * @return
     */
    public static String getDeviceImei(Context context) {
        String deviceIMei = "";
        if (context != null) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                if (Build.VERSION.SDK_INT > 26 && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    deviceIMei = tm.getImei();
                }
            }
        }
        return deviceIMei == null ? "" : deviceIMei;
    }

    /**
     * 返回app版本名字
     * 对应build.gradle中的versionName
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName == null ? "" : versionName;
    }

    /**
     * 返回app版本号
     * 对应build.gradle中的versionCode
     *
     * @param context
     * @return
     */
    public static String getAppVersionCode(Context context) {
        String versionCode = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = String.valueOf(packInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode == null ? "" : versionCode;
    }

    /**
     * 获取AndroidManifest.xml里 的值
     *
     * @param context
     * @param name
     * @return
     */
    public static String getMetaData(Context context, String name) {
        String value = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * dp 转化为 px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px 转化为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取设备宽度（px）
     *
     * @param context
     * @return
     */
    public static int deviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     *
     * @param context
     * @return
     */
    public static int deviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * SD卡判断
     *
     * @return
     */
    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 是否有网
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable() && networkInfo.isConnected();
            }
        }
        return false;
    }

    public static boolean isSDKAbove(int sdkInt) {
        return Build.VERSION.SDK_INT >= sdkInt;
    }

    public static void printWifiRssi(Context context) {
        WifiManager wifi_service = (WifiManager) context.getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifi_service.getConnectionInfo();
        if (wifiInfo != null) {
            Log.e("WIFI", "WIFI 强度：" + wifiInfo.getRssi());
            Log.e("WIFI", "WIFI 速度：" + wifiInfo.getLinkSpeed());
        }
    }

    public static String getSystemProperty() {
        String line;
        BufferedReader input = null;

        try {
            Process p = Runtime.getRuntime().exec("getprop ro.product.firmware");
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e("Unable to read sysprop ", ex.getMessage());
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e("closing InputStream", e.getMessage());
                }
            }
        }
        Log.v("[ROM Version]", line);
        return line;
    }


    public static boolean checkApkExist(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return installed;
    }
}
