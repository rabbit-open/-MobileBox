package com.hualala.mobilebox.module.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.R;
import com.hualala.ui.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.ui.widget.recyclelib.SupetRecyclerViewHolder;

public class WifiAdapter extends SupetRecyclerAdapter<Object> {

    public WifiAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public SupetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType, SupetRecyclerAdapter adapter) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_settings_layout, parent, false);
        return new SupetRecyclerViewHolder(itemView, adapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SupetRecyclerViewHolder holder, int position) {

        TextView name = holder.itemView.findViewById(R.id.name);
        if (getData(position) instanceof WifiInfo) {
            WifiInfo temp = ((WifiInfo) getData(position));
            name.setText(temp.getSSID().replace("\"", "") + "   " + temp.getSupplicantState().name());
            name.setOnClickListener(null);
        }

        if (getData(position) instanceof WifiBean) {
            name.setText(((WifiBean) getData(position)).getWifiName());
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickNet((WifiBean) getData(position));
                }
            });
        }
    }


    public void onclickNet(WifiBean wifiBean) {
        final EditText editText = new EditText(getContext());
        editText.setText(WifiConfig.getSSID(wifiBean.getScanResult().SSID));

        new AlertDialog.Builder(getContext())
                .setTitle("输入密码")
                .setView(editText)
                .setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        WifiConfig.putSSID(wifiBean.getScanResult().SSID, editText.getText().toString());
                        connectToHotpot(wifiBean.getScanResult(), editText.getText().toString(), WifiUtils
                                .getWifiCipher(wifiBean.getCapabilities()));
                    }
                }).create().show();
    }

    /*连接到热点*/
    public void connectToHotpot(ScanResult ssid, String pass, WifiUtils.WifiCipherType type) {
        //需要root权限,不能删除，修改，关闭网络权限
        if (Build.VERSION.SDK_INT < 27) {
            WifiUtils.addNetWork(WifiUtils.createWifiConfig(getContext(), ssid, pass, type), getContext());
        } else {
            ToastUtils.showToastCenter(getContext(), "不能使用删除，修改，关闭网络系统API,需要root权限，系统权限");
        }
    }

}