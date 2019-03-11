package com.hualala.bi.framework.base;

import android.support.v4.app.FragmentActivity;

public class BaseContractor {

    private FragmentActivity lifecycleOwner;

    public FragmentActivity getLifecycleOwner() {
        return lifecycleOwner;
    }

    public BaseContractor(FragmentActivity lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public FragmentActivity getContext() {
        return lifecycleOwner;
    }
}
