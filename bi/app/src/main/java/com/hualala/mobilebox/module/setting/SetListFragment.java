package com.hualala.mobilebox.module.setting;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.R2;
import com.hualala.mobilebox.base.BaseFragment;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.module.boot.viewmodel.MainShareViewModel;
import com.hualala.ui.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.ui.widget.recyclelib.SupetRecyclerView;
import com.hualala.ui.widget.recyclelib.SupetStaggeredGridLayoutManager;

public class SetListFragment extends BaseFragment {

    @BindView(R2.id.list)
    public SupetRecyclerView mListView;

    private SupetRecyclerAdapter adapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_sets_fragment;
    }

    @Override
    public void findViews(View view) {
        ButterKnife.bind(this, view);

        mListView.setLayoutManager(new SupetStaggeredGridLayoutManager(1, SupetStaggeredGridLayoutManager.VERTICAL));
        adapter = new SettingsAdapter(getContext(), this);
        mListView.setAdapter(adapter);

    }


    @Override
    public void setListeners() {

    }

    @Override
    public void process() {

    }

    public static SetListFragment newInstance() {
        Bundle bundle = new Bundle();
        SetListFragment fragment = new SetListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UINavgation.ScanCode) {
            IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
            if (result.getContents() == null) {
                ToastUtils.showToastCenter(getActivity(), "扫码失败");
            } else {
                if (result.getContents().endsWith("/")) {
                    changeServiceAddress(result.getContents());
                    ToastUtils.showToastCenter(getActivity(), "成功切换远程地址");
                } else {
                    ToastUtils.showToastCenter(getActivity(), "非法地址");
                }
            }
        }
    }

    private void changeServiceAddress(String ip) {
        if (!TextUtils.isEmpty(ip)) {
            if (ip.endsWith("/")) {
                MBBusinessContractor.getBusinessContractor().getTerminalDataRepository().changeServerAddr(ip);
                MBBusinessContractor.getBusinessContractor().getGeneralConfig().getCloudServerInfo().setBaseApiUrl(ip);
                MainShareViewModel model = ViewModelProviders.of(this).get(MainShareViewModel.class);
                model.select(true);
                ToastUtils.showToastCenter(getActivity(), "成功切换本地服务");
            } else {
                ToastUtils.showToastCenter(getActivity(), "非法地址");
            }
        }
    }
}
