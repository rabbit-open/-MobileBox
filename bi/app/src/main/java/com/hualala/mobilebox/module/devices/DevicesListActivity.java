package com.hualala.mobilebox.module.devices;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.hualala.bi.framework.base.BaseContractorActivity;
import com.hualala.mobilebox.R;
import com.hualala.server.api.DeviceBean;
import com.hualala.server.api.DeviceSearcher;
import com.hualala.ui.widget.PullLoadMoreView;
import com.hualala.ui.widget.recyclelib.SupetRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DevicesListActivity extends BaseContractorActivity {

    private PullLoadMoreView pullLoadMoreView;
    private SupetRecyclerView recyclerView;
    private DevicesAdapter devicesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        pullLoadMoreView = findViewById(R.id.pullLoadView);
        recyclerView = findViewById(R.id.list);
        pullLoadMoreView.setContentView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        devicesAdapter = new DevicesAdapter(this);
        recyclerView.setAdapter(devicesAdapter);
        pullLoadMoreView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                searchDevices_broadcast();
            }
        });

        pullLoadMoreView.showLoading();

        searchDevices_broadcast();
    }

    private List<DeviceBean> mDeviceList = new ArrayList<>();

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            pullLoadMoreView.showContent();
            pullLoadMoreView.onRefreshCompleted();
            devicesAdapter.addHomePage(mDeviceList);
            return false;
        }
    });

    private void searchDevices_broadcast() {
        new DeviceSearcher() {
            @Override
            public void onSearchStart() {

            }

            @Override
            public void onSearchFinish(Set deviceSet) {
                mDeviceList.clear();
                mDeviceList.addAll(deviceSet);
                mHandler.sendEmptyMessage(0); // 在UI上更新设备列表
            }
        }.start();
    }
}
