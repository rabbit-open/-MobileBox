package com.hualala.mobilebox.module;

import com.hualala.bi.framework.application.MBApplication;
import com.hualala.ui.widget.fresco.FrescoUtils;

public class App extends MBApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FrescoUtils.init(this);
    }
}
