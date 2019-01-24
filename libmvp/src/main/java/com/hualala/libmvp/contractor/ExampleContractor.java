package com.hualala.libmvp.contractor;

import android.arch.lifecycle.LifecycleOwner;
import android.view.View;
import com.hualala.libmvp.example.MainContractorVMPresenter;
import com.hualala.libmvp.viewmodel.ExampleView;

public class ExampleContractor extends BaseContractor {

    private MainContractorVMPresenter presenter;
    private ExampleView mView;

    public ExampleContractor(View view, LifecycleOwner lifecycleOwner) {
        super(view, lifecycleOwner);
        this.mView = new ExampleView(view);
        presenter = new MainContractorVMPresenter(lifecycleOwner);
        presenter.setView(mView);
    }

}
