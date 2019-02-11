package com.hualala.mobilebox.module.setting;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.zxing.WriterException;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.module.zxing.QRCodeUtils;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerViewHolder;
import com.sample.commondialog.QRDialog;

public class SettingsAdapter extends SupetRecyclerAdapter<Object> {

    private Fragment fragment;

    public SettingsAdapter(Context mContext, Fragment fragment) {
        super(mContext);
        this.fragment = fragment;
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
    public int getItemCount() {
        return 2;
    }

    @Override
    public void onBindViewHolder(SupetRecyclerViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.name);
        if (position == 0) {
            name.setText("查看本地服务器地址");
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = MBBusinessContractor.getBaseUrl();
                    try {
                        QRDialog.newInstance()
                                .setTitle("本机服务器地址二维码")
                                .setImage(QRCodeUtils.CreateTwoDCode(url))
                                .setContent(url)
                                .setMargin(60)
                                .setOutCancel(true)
                                .show(((FragmentActivity) getContext()).getSupportFragmentManager());
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if (position == 1) {
            name.setText("切换远程服务器");
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UINavgation.startScanCodeActivity(fragment);
                }
            });

        }
    }
}