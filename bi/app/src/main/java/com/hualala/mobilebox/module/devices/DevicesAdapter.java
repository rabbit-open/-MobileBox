package com.hualala.mobilebox.module.devices;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hualala.bi.framework.application.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.server.api.DeviceBean;
import com.hualala.ui.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.ui.widget.recyclelib.SupetRecyclerViewHolder;

public class DevicesAdapter extends SupetRecyclerAdapter<DeviceBean> {

    public DevicesAdapter(Context mContext) {
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

    @Override
    public void onBindViewHolder(SupetRecyclerViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.name);
        DeviceBean bean = getData(position);

        name.setText(String.format("%s--%s:%d", bean.getRoom(), bean.getIp(), bean.getPort()));
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeServiceAddress("http://" + bean.getIp() + ":" + bean.getPort() + "/");
            }
        });
    }

    private void changeServiceAddress(String ip) {
        if (!TextUtils.isEmpty(ip)) {
            MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr(ip);
            MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().setBaseApiUrl(ip);
        }
    }

}