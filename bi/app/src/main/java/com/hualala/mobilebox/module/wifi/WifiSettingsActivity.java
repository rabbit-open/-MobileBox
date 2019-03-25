package com.hualala.mobilebox.module.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.hualala.bi.framework.base.BaseContractorActivity;
import com.hualala.libutils.system.DeviceUtils;
import com.hualala.mobilebox.R;
import com.hualala.ui.widget.recyclelib.SupetRecyclerView;

import java.util.List;

public class WifiSettingsActivity extends BaseContractorActivity {

    private static final String TAG = WifiSettingsActivity.class.getSimpleName() + "-[wifi]";

    private BroadcastReceiver mWifiBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (NetworkInfo.State.DISCONNECTED == info.getState()) {//wifi没连接上
                    Log.v(TAG, "wifi没连接上");
                } else if (NetworkInfo.State.CONNECTED == info.getState()) {//wifi连接上了
                    updateWifi();
                    Log.v(TAG, "wifi连接上了");
                } else if (NetworkInfo.State.CONNECTING == info.getState()) {//正在连接
                    Log.v(TAG, "wifi正在连接");
                }
            } else if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())) {
                boolean isScanned = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, true);
                if (isScanned) {
                    Log.v(TAG, "wifi扫描成功");
                    updateWifi();
                }
            }
        }
    };
    private SupetRecyclerView recyclerView;
    private WifiAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_settings);
        initView();
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

    private void updateWifi() {

        mAdapter.addHomePage(null);

        if (DeviceUtils.isNetworkConnected(this) && getCurrentUseWifiInfo() != null) {
            mAdapter.addNextPage(getCurrentUseWifiInfo());
        }

        List<WifiBean> wifis = WifiUtils.getWifiList(this);
        for (WifiBean wifi : wifis) {
            if (DeviceUtils.isNetworkConnected(this) && ("\"" + wifi.getWifiName() + "\"").equals(getCurrentUseWifiInfo().getSSID())) {
                continue;
            }
            mAdapter.addNextPage(wifi);
        }
    }

    private WifiManager getWifiManager() {
        return (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    private WifiInfo getCurrentUseWifiInfo() {
        WifiManager wifi = getWifiManager();
        WifiInfo wifiInfo = getWifiManager().getConnectionInfo();
        if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            return wifiInfo;
        }
        return null;
    }
}
