package com.hualala.mobilebox.base;

import android.arch.lifecycle.LifecycleOwner;
import android.view.View;

public class BaseContractor {

    private LifecycleOwner lifecycleOwner;
    private View rootView;


    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    public BaseContractor(View view, LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        this.rootView = view;
    }
}
