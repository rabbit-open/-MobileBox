package com.hualala.libmvp.example;

import com.hualala.libmvp.contractor.IExampleContractor;
import com.hualala.libmvp.presenter.BasePresenter;
import io.reactivex.disposables.Disposable;

public class MainRxPresenter extends BasePresenter<MVPRXActivity> implements IExampleContractor.IExamplePresenter {

    //RX观察者
    private Disposable useCase;

    @Override
    public void destroy() {
        super.destroy();
        dispose(useCase);
    }

    @Override
    public void getList() {
        getView().showName();

    }
}
