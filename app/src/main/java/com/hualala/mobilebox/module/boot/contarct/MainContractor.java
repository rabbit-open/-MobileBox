package com.hualala.mobilebox.module.boot.contarct;

import android.arch.lifecycle.LifecycleOwner;
import android.view.View;
import com.hualala.mobilebox.base.BaseContractor;
import com.hualala.mobilebox.module.boot.presenter.MainContractorPresenter;
import com.hualala.mobilebox.module.boot.view.MainView;

public class MainContractor extends BaseContractor {

    private MainContractorPresenter presenter;
    private MainView mView;

    public MainContractor(View view, LifecycleOwner lifecycleOwner) {
        super(view, lifecycleOwner);
        this.mView = new MainView(view);
        presenter = new MainContractorPresenter(lifecycleOwner);
        presenter.setView(mView);
        this.mView.setListener(presenter);
    }

}
