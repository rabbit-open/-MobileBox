package com.hualala.libmvp.example;

import com.hualala.libmvp.contractor.IExampleContractor;
import com.hualala.libmvp.presenter.BasePresenter;
import io.reactivex.disposables.Disposable;

public class MainLifeCyclePresenter extends BasePresenter<MVPLifeCycleActivity> implements IExampleContractor.IExamplePresenter {

    @Override
    public void getList() {
        //数据回来
    }

    //RX观察者
    private Disposable useCase;

    @Override
    public void destroy() {
        super.destroy();
        dispose(useCase);
    }
}
