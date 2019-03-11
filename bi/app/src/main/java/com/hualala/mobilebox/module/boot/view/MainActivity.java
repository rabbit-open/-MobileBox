package com.hualala.mobilebox.module.boot.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hualala.bi.framework.application.MBBusinessContractor;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.libutils.wifi.WifiUtils;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.module.boot.contarct.MainTabVpContractor;
import com.hualala.mobilebox.module.boot.viewmodel.MainShareViewModel;

public class MainActivity extends AppCompatActivity {

    MainTabVpContractor contract;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeServiceAddress();
        contract = new MainTabVpContractor(this);
    }

    private void changeServiceAddress() {
        String ip = WifiUtils.getWifiIp(this);
        if (!TextUtils.isEmpty(ip)) {
            MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr("http://" + ip + ":8888/");
            MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().setBaseApiUrl("http://" + ip + ":8888/");
        }
    }

    private void changeServiceAddress(String ip) {
        if (!TextUtils.isEmpty(ip)) {
            MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr(ip);
            MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().setBaseApiUrl(ip);
            MainShareViewModel model = ViewModelProviders.of(this).get(MainShareViewModel.class);
            model.select(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UINavgation.ScanCode) {
            IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
            if (result.getContents() != null) {
                changeServiceAddress(result.getContents());
                ToastUtils.showToastCenter(this, "扫码成功");
            }
        }
    }
}
