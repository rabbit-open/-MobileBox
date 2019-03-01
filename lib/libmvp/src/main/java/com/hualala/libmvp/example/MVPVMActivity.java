package com.hualala.libmvp.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hualala.libmvp.contractor.IExampleContractor;
import com.hualala.libmvp.presenter.BaseLifeCycleActivity;

public class MVPVMActivity extends BaseLifeCycleActivity implements  IExampleContractor.IExampleView  {

    private MainVMPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainVMPresenter(this);
        presenter.setView(this);
    }

    @Override
    public void showName() {

    }
}
