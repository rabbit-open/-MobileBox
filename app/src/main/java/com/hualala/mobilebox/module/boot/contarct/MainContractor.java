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
import com.hualala.mobilebox.base.BaseContractor;
import com.hualala.mobilebox.module.boot.presenter.MainContractorPresenter;
import com.hualala.mobilebox.module.boot.view.MainView;
import com.hualala.mobilebox.module.boot.viewmodel.MainShareViewModel;

public class MainContractor extends BaseContractor {

    private MainContractorPresenter presenter;
    private MainView mView;

    public MainContractor(FragmentActivity lifecycleOwner) {
        super(lifecycleOwner);

        View rootView = LayoutInflater.from(lifecycleOwner).inflate(R.layout.activity_main, null);
        lifecycleOwner.setContentView(rootView);

        mView = new MainView(rootView);

        presenter = new MainContractorPresenter(lifecycleOwner);
        presenter.setView(mView);

        mView.setListener(presenter);

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
