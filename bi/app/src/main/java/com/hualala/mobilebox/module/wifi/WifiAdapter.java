package com.hualala.mobilebox.module.wifi;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SupetRecyclerViewHolder holder, int position) {

        TextView name = holder.itemView.findViewById(R.id.name);
        if (getData(position) instanceof NetworkInfo) {
            NetworkInfo temp = ((NetworkInfo) getData(position));
            name.setText(temp.getExtraInfo().replace("\"", "") + "   " + getFormatContent(temp.getDetailedState()));
            name.setOnClickListener(null);
        }

        if (getData(position) instanceof WifiInfo) {
            WifiInfo temp = ((WifiInfo) getData(position));
            name.setText(temp.getSSID().replace("\"", "") + "   " + getFormatContent(temp.getSupplicantState()));
            name.setOnClickListener(null);
        }

        if (getData(position) instanceof WifiBean) {
            name.setText(((WifiBean) getData(position)).getWifiName() + "   " + ((WifiBean) getData(position)).getState());
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickNet((WifiBean) getData(position));
                }
            });
        }
    }


    //https://blog.csdn.net/woty123/article/details/7886227
    public static String getFormatContent(SupplicantState state) {
        if (state.ordinal() == SupplicantState.ASSOCIATED.ordinal()) {
            return "关联完成";
        } else if (state.ordinal() == SupplicantState.ASSOCIATING.ordinal()) {
            return "开始关联到一个BSS或SSID";
        } else if (state.ordinal() == SupplicantState.COMPLETED.ordinal()) {
            return "所有用户认证已完成";
        } else if (state.ordinal() == SupplicantState.DISCONNECTED.ordinal()) {
            return "当前接入点不可关联,有可能开始下一个关联";
        } else if (state.ordinal() == SupplicantState.DORMANT.ordinal()) {
            return "Android特有，当用户明确发出中断指令";
        } else if (state.ordinal() == SupplicantState.FOUR_WAY_HANDSHAKE.ordinal()) {
            return "WPA 4 次握手完成";
        } else if (state.ordinal() == SupplicantState.GROUP_HANDSHAKE.ordinal()) {
            return "WPA group 握手完成";
        } else if (state.ordinal() == SupplicantState.INACTIVE.ordinal()) {
            return " 处于非活动状态（wpa_supplicant被禁用）";
        } else if (state.ordinal() == SupplicantState.INVALID.ordinal()) {
            return "伪状态，通常不应被看到";
        } else if (state.ordinal() == SupplicantState.SCANNING.ordinal()) {
            return "正在扫描网络";
        } else if (state.ordinal() == SupplicantState.UNINITIALIZED.ordinal()) {
            return "还未连接到wpa_supplicant";
        }
        return "未知状态";
    }


    //https://blog.csdn.net/u012947056/article/details/52194379
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static String getFormatContent(NetworkInfo.DetailedState state) {
        if (state.ordinal() == NetworkInfo.DetailedState.IDLE.ordinal()) {
            return "空闲";
        } else if (state.ordinal() == NetworkInfo.DetailedState.SCANNING.ordinal()) {
            return "正在扫描";
        } else if (state.ordinal() == NetworkInfo.DetailedState.AUTHENTICATING.ordinal()) {
            return "正在进行身份验证...";
        } else if (state.ordinal() == NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()) {
            return "正在获取Ip地址";
        } else if (state.ordinal() == NetworkInfo.DetailedState.CONNECTED.ordinal()) {
            return "已连接";
        } else if (state.ordinal() == NetworkInfo.DetailedState.SUSPENDED.ordinal()) {
            return "已暂停";
        } else if (state.ordinal() == NetworkInfo.DetailedState.DISCONNECTING.ordinal()) {
            return "正在断开连接...";
        } else if (state.ordinal() == NetworkInfo.DetailedState.DISCONNECTED.ordinal()) {
            return "已断开";
        } else if (state.ordinal() == NetworkInfo.DetailedState.FAILED.ordinal()) {
            return "失败";
        } else if (state.ordinal() == NetworkInfo.DetailedState.BLOCKED.ordinal()) {
            return "已阻止";
        } else if (state.ordinal() == NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal()) {
            return "暂时关闭（网络状况不佳）";
        } else if (state.ordinal() == NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal()) {
            return "判断是否需要浏览器二次登录";
        }
        return "未知状态";
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