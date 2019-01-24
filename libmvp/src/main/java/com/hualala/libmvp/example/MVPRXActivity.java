package com.hualala.libmvp.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hualala.libmvp.contractor.IExampleContractor;
import com.hualala.libmvp.presenter.BaseRxActivity;
import com.hualala.libmvp.presenter.HasPresenter;

public class MVPRXActivity extends BaseRxActivity implements HasPresenter<MainRxPresenter>, IExampleContractor.IExampleView {


    private MainRxPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainRxPresenter();
        presenter.setView(this);

        presenter.getList();
    }

    @Override
    public MainRxPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showName() {

    }
}
