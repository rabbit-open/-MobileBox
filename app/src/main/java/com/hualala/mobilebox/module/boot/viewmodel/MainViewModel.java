package com.hualala.mobilebox.module.boot.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<MainViewData> data = new MutableLiveData<>();

    public MutableLiveData<MainViewData> getData() {
        return data;
    }

    public void updateData(MainViewData list) {
        data.setValue(list);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
