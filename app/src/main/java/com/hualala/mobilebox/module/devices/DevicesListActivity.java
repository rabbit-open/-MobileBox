package com.hualala.mobilebox.module.devices;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hualala.mobilebox.base.BaseContractorActivity;
import com.hualala.mobilebox.widget.PullLoadMoreView;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DevicesListActivity extends BaseContractorActivity {

    private PullLoadMoreView pullLoadMoreView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pullLoadMoreView = new PullLoadMoreView(this);
        setContentView(pullLoadMoreView);

        initData();
        searchDevices_broadcast();
    }

    private void initData() {
        new DeviceWaitingSearch(this, "日灯光", "客厅") {
            @Override
            public void onDeviceSearched(InetSocketAddress socketAddr) {
                Log.v("test", "已上线，搜索主机：" + socketAddr.getAddress().getHostAddress() + ":" + socketAddr.getPort());
            }
        }.start();
    }


    private List<DeviceSearcher.DeviceBean> mDeviceList = new ArrayList<>();

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.v("tag", mDeviceList.get(0).room);
            Log.v("tag", mDeviceList.get(0).ip);
            Log.v("tag", mDeviceList.get(0).port + "");
            return false;
        }
    });

    private void searchDevices_broadcast() {
        new DeviceSearcher() {
            @Override
            public void onSearchStart() {
                // startSearch(); // 主要用于在UI上展示正在搜索
            }

            @Override
            public void onSearchFinish(Set deviceSet) {
                //endSearch(); // 结束UI上的正在搜索
                mDeviceList.clear();
                mDeviceList.addAll(deviceSet);
                mHandler.sendEmptyMessage(0); // 在UI上更新设备列表
            }
        }.start();
    }
}
