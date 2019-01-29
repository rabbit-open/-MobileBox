package com.hualala.mobilebox.base;

import android.support.v4.app.FragmentActivity;
import android.view.View;

public class BaseContractor {

    private FragmentActivity lifecycleOwner;
    private View rootView;


    public FragmentActivity getLifecycleOwner() {
        return lifecycleOwner;
    }

    public BaseContractor(View view, FragmentActivity lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        this.rootView = view;
    }
}
