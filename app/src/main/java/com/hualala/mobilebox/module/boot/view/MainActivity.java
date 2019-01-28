package com.hualala.mobilebox.module.boot.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.hualala.libutils.wifi.WifiUtils;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.module.boot.contarct.MainContractor;

public class MainActivity extends AppCompatActivity {

    MainContractor contract;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String ip = WifiUtils.getWifiIp(this);
        if (!TextUtils.isEmpty(ip)) {
            MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr("http://" + ip + ":8888/");
            MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().setBaseApiUrl("http://" + ip + ":8888/");
        }
        contract = new MainContractor(getWindow().getDecorView(), this);
    }

}
