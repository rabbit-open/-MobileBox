package com.hualala.mobilebox.module.setting;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.hualala.bi.framework.application.MBBusinessContractor;
import com.hualala.domain.interactor.DefaultObserver;
import com.hualala.domain.usecase.PhoneListUseCase;
import com.hualala.libutils.MBContext;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.module.boot.viewmodel.MainShareViewModel;
import com.hualala.mobilebox.module.zxing.QRCodeUtils;
import com.hualala.server.phone.ContactsBean;
import com.hualala.server.phone.PhoneUtils;
import com.hualala.ui.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.ui.widget.recyclelib.SupetRecyclerViewHolder;
import com.sample.commondialog.QRDialog;

import java.util.List;

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
                    String url = MBBusinessContractor.getDeviceBaseUrl();
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
        } else if (position == 1) {
            name.setText("切换本地服务器");
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeServiceAddress(MBBusinessContractor.getDeviceBaseUrl());
                }
            });
        }

//        else if (position == 2) {
//            name.setText("联系人迁移[需要授权]");
//            name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    readContacts();
//                }
//            });
//        }
    }

    private void changeServiceAddress(String ip) {
        if (!TextUtils.isEmpty(ip)) {
            MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr(ip);
            MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().setBaseApiUrl(ip);
            MainShareViewModel model = ViewModelProviders.of(fragment.getActivity()).get(MainShareViewModel.class);
            model.select(true);
            ToastUtils.showToastCenter(getContext(),"切换本地服务成功");
        }
    }


    private void readContacts() {
        PhoneListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(PhoneListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<ContactsBean>>() {

            List<ContactsBean> mVideos;

            @Override
            public void onNext(List<ContactsBean> videos) {
                mVideos = videos;
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
                ToastUtils.showToastCenter(MBContext.getInstance(), throwable.getMessage());
            }

            @Override
            public void onComplete() {
                super.onComplete();
                if (mVideos != null) {
                    for (int i = 0; i < mVideos.size(); i++) {
                        ContactsBean bean = mVideos.get(i);
                        PhoneUtils.insertData(MBContext.getInstance(), bean.getName(), bean.getPhone());
                        Log.v("insertData", bean.getName() + "--" + bean.getPhone());
                    }
                }
            }

        }, null);
    }
}