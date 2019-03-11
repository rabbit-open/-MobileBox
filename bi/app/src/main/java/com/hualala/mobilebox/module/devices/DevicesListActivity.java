package com.hualala.mobilebox.module.devices;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hualala.bi.framework.application.MBBusinessContractor;
import com.hualala.bi.framework.base.BaseContractorActivity;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.server.api.DeviceBean;
import com.hualala.server.api.DeviceSearcher;
import com.hualala.ui.widget.CommonHeader;
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
    private CommonHeader commonHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        pullLoadMoreView = findViewById(R.id.pullLoadView);
        recyclerView = findViewById(R.id.list);
        commonHeader = findViewById(R.id.commonHeader);

        commonHeader.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UINavgation.startScanCodeActivity(DevicesListActivity.this);
            }
        });

        pullLoadMoreView.setContentView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        devicesAdapter = new DevicesAdapter(this);
        recyclerView.setAdapter(devicesAdapter);
        pullLoadMoreView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                searchDevices();
            }
        });
        pullLoadMoreView.showLoading();
        searchDevices();
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

    private void searchDevices() {
        new DeviceSearcher() {
            @Override
            public void onSearchStart() {
            }

            @Override
            public void onSearchFinish(Set deviceSet) {
                mDeviceList.clear();
                mDeviceList.addAll(deviceSet);
                mHandler.sendEmptyMessage(0);
            }
        }.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UINavgation.ScanCode) {
            IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
            if (result.getContents() != null) {
                changeServiceAddress(result.getContents());
            }
        }
    }

    private void changeServiceAddress(String ip) {
        if (!TextUtils.isEmpty(ip)) {
            if (ip.endsWith("/")) {
                MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr(ip);
                MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().setBaseApiUrl(ip);
                ToastUtils.showToastCenter(this, "成功切换远程地址");
                finish();
            } else {
                ToastUtils.showToastCenter(this, "非法地址");
            }
        }
    }

}
