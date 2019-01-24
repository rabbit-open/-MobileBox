package com.hualala.libmvp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ExampleViewModel extends ViewModel {

    public MutableLiveData<ExampleViewData> data = new MutableLiveData<>();

    public MutableLiveData<ExampleViewData> getData() {
        return data;
    }

    public void updateData(ExampleViewData list) {
        data.setValue(list);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
