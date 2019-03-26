package com.hualala.mobilebox.module.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;

import com.hualala.bi.framework.base.BaseContractorActivity;
import com.hualala.mobilebox.R;
import com.hualala.ui.widget.recyclelib.SupetRecyclerView;

import java.util.List;

public class WifiSettingsActivity extends BaseContractorActivity {

    private static final String TAG = WifiSettingsActivity.class.getSimpleName() + "-[wifi]";

    private BroadcastReceiver mWifiBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())) {
                boolean isScanned = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, true);
                if (isScanned) {
                    Log.v(TAG, "wifi扫描成功");
                    String ip = getWifiIp(context);
                    if (!TextUtils.isEmpty(ip)) {
                        onWifiConnected(ip);
                    } else {
                        onWifiDisconnected();
                    }
                }
            } else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                Parcelable parcelableExtra = intent
                        .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (null != parcelableExtra) {
                    NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                    checkWifiState(networkInfo.getState());
                }
            }
        }
    };

    private void checkWifiState(NetworkInfo.State state) {
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            if (state == NetworkInfo.State.CONNECTED) {
                String ip = getWifiIp(this);
                if (!TextUtils.isEmpty(ip)) {
                    onWifiConnected(ip);
                    return;
                }
            }
            onWifiConnecting();
            return;
        }
        onWifiDisconnected();
    }

    private void onWifiDisconnected() {
        Log.v(TAG, "断开连接");
        updateWifi(null);
    }

    private void onWifiConnecting() {
        Log.v(TAG, "连接中...");
        updateWifi(getCurrentUseWifiInfo());
    }

    private void onWifiConnected(String ip) {
        Log.v(TAG, ip);
        updateWifi(getCurrentUseWifiInfo());
    }

    private synchronized void updateWifi(WifiInfo wifiInfo) {
        mAdapter.addHomePage(null);
        if (wifiInfo != null) {
            mAdapter.insertHomePage(wifiInfo);
        }
        List<WifiBean> wifis = WifiUtils.getWifiList(this);
        for (WifiBean wifi : wifis) {
            if (wifiInfo != null && (wifi.getWifiName()).equals(wifiInfo.getSSID().replace("\"", ""))) {
                //empty
            } else {
                mAdapter.addNextPage(wifi);
            }
        }
    }


    public static String getWifiIp(Context context) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifimanager.getConnectionInfo();
        if (wifiInfo != null && wifiInfo.getIpAddress() > 0) {
            return intToIp(wifiInfo.getIpAddress());
        }
        return null;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 24) & 0xFF);
    }

    private SupetRecyclerView recyclerView;
    private WifiAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_settings);
        initView();
        openWifi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(mWifiBroadcastReceiver, filter);

        WifiUtils.startScan(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mWifiBroadcastReceiver);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new WifiAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private WifiManager getWifiManager() {
        return (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    private WifiInfo getCurrentUseWifiInfo() {
        return getWifiManager().getConnectionInfo();
    }

    // 打开WIFI
    public void openWifi() {
        if (!getWifiManager().isWifiEnabled()) {
            getWifiManager().setWifiEnabled(true);
        }
    }

    // 关闭WIFI
    public void closeWifi() {
        if (getWifiManager().isWifiEnabled()) {
            getWifiManager().setWifiEnabled(false);
        }
    }
}
