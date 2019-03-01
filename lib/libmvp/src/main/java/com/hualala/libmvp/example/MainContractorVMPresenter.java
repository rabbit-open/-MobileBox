package com.hualala.libmvp.example;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import com.hualala.libmvp.contractor.IExampleContractor;
import com.hualala.libmvp.presenter.BasePresenter;
import com.hualala.libmvp.viewmodel.ExampleView;
import com.hualala.libmvp.viewmodel.ExampleViewData;
import com.hualala.libmvp.viewmodel.ExampleViewModel;

public class MainContractorVMPresenter extends BasePresenter<ExampleView> implements IExampleContractor.IExamplePresenter {

    //自动解耦NET
    private ExampleViewModel viewModel = new ExampleViewModel();//不需关心Rxjava 结果

    public MainContractorVMPresenter(LifecycleOwner lifecycleOwner) {
        viewModel.getData().observe(lifecycleOwner, new Observer<ExampleViewData>() {
            @Override
            public void onChanged(@Nullable ExampleViewData exampleViewData) {
                mView.showName();
            }
        });
    }

    @Override
    public void getList() {
        //数据回来
        viewModel.getData().postValue(new ExampleViewData());
    }
}
