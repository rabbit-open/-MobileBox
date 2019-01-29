package com.hualala.mobilebox.base;

import android.support.v4.app.FragmentActivity;
import android.view.View;

public class BaseContractor {

    private FragmentActivity lifecycleOwner;

    public FragmentActivity getLifecycleOwner() {
        return lifecycleOwner;
    }

    public BaseContractor(FragmentActivity lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }
}
