package com.hualala.mobilebox.module.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.hualala.bi.framework.base.BaseContractorActivity;
import com.hualala.mobilebox.R;
import com.hualala.ui.widget.recyclelib.SupetRecyclerView;

import java.util.List;

public class WifiSettingsActivity extends BaseContractorActivity {

    private BroadcastReceiver mWifiBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(intent.getAction())) {
                boolean isScanned = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, true);
                if (isScanned) {
                    onWifiConnecting();
                }
            } else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                Parcelable parcelableExtra = intent
                        .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (null != parcelableExtra) {
                    networkInfo = (NetworkInfo) parcelableExtra;
                    updateWifi(networkInfo);
                } else {
                    networkInfo = null;
                    updateWifi(null);
                }

            } else if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                onWifiConnecting();
            } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                onWifiConnecting();
            }
        }
    };

    private NetworkInfo networkInfo;

    private void onWifiConnecting() {
        updateWifi(networkInfo);
    }

    public static final String NONE = "<unknown ssid>";

    private synchronized void updateWifi(NetworkInfo wifiInfo) {
        mAdapter.addHomePage(null);

        if (WifiUtils.isOpenWifi(this)) {
            if (wifiInfo != null && !wifiInfo.getExtraInfo().contains(NONE)) {
                mAdapter.insertHomePage(wifiInfo);
            }
            List<WifiBean> wifis = WifiUtils.getWifiList(this);
            for (WifiBean wifi : wifis) {
                if (wifiInfo != null && (wifi.getWifiName()).equals(wifiInfo.getExtraInfo().replace("\"", ""))) {
                    //empty
                } else {
                    mAdapter.addNextPage(wifi);
                }
            }
        }
    }

    private SupetRecyclerView recyclerView;
    private WifiAdapter mAdapter;
    private CheckBox sswitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_settings);
        initView();
        sswitch.setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mWifiBroadcastReceiver, filter);
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
        sswitch = findViewById(R.id.checkbox);
        sswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    WifiUtils.openWifi(getApplicationContext());
                    WifiUtils.startScan(getApplicationContext());
                } else {
                    WifiUtils.closeWifi(getApplicationContext());
                    mAdapter.addHomePage(null);
                }
            }
        });
    }

}
