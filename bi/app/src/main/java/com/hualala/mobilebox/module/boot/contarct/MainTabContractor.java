package com.hualala.mobilebox.module.boot.contarct;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import com.hualala.libutils.MBContext;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.R;
import com.hualala.bi.framework.base.BaseContractor;
import com.hualala.mobilebox.module.boot.presenter.MainTabContractorPresenter;
import com.hualala.mobilebox.module.boot.view.IMainView;
import com.hualala.mobilebox.module.boot.view.MainTabView;
import com.hualala.mobilebox.module.boot.viewmodel.MainShareViewModel;

public class MainTabContractor extends BaseContractor {

    private MainTabContractorPresenter presenter;
    private MainTabView mView;

    public MainTabContractor(FragmentActivity lifecycleOwner) {
        super(lifecycleOwner);

        View rootView = LayoutInflater.from(lifecycleOwner)
                .inflate(R.layout.activity_main_tablayout, null);
        lifecycleOwner.setContentView(rootView);

        mView = new MainTabView(rootView);

        presenter = new MainTabContractorPresenter(lifecycleOwner, mView);

        mView.setListener(new IMainView.IMainViewListener() {
            @Override
            public void getMediaVideo() {
                presenter.getMediaVideo();
            }

            @Override
            public void getMediaAudio() {
                presenter.getMediaAudio();
            }

            @Override
            public void getMediaImage() {
                presenter.getMediaImage();
            }
        });

        presenter.getMediaImage();

        MainShareViewModel model = ViewModelProviders.of(getLifecycleOwner()).get(MainShareViewModel.class);
        model.getData().observe(getLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    mView.refresh();
                    ToastUtils.showToastCenter(MBContext.getContext(), "切换服务成功");
                }
            }
        });
    }
}
