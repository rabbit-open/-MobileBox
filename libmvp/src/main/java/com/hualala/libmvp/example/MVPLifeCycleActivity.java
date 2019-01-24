package com.hualala.libmvp.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hualala.libmvp.contractor.IExampleContractor;
import com.hualala.libmvp.presenter.BaseLifeCycleActivity;

public class MVPLifeCycleActivity extends BaseLifeCycleActivity implements  IExampleContractor.IExampleView {


    private MainLifeCyclePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainLifeCyclePresenter();
        presenter.setView(this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    public void showName() {

    }
}
