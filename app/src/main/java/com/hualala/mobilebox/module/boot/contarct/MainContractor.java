package com.hualala.mobilebox.module.boot.contarct;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.hualala.libutils.MBContext;
import com.hualala.libutils.view.ToastUtils;
import com.hualala.mobilebox.base.BaseContractor;
import com.hualala.mobilebox.module.boot.presenter.MainContractorPresenter;
import com.hualala.mobilebox.module.boot.view.MainView;
import com.hualala.mobilebox.module.boot.viewmodel.MainShareViewModel;

public class MainContractor extends BaseContractor {

    private MainContractorPresenter presenter;
    private MainView mView;

    public MainContractor(View view, FragmentActivity lifecycleOwner) {
        super(view, lifecycleOwner);
        presenter = new MainContractorPresenter(lifecycleOwner);
        this.mView = new MainView(view);
        this.mView.setListener(presenter);
        presenter.setView(mView);
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
