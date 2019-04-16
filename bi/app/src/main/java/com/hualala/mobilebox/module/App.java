package com.hualala.mobilebox.module;

import com.billy.android.loading.Gloading;
import com.billy.android.loading.GlobalAdapter;
import com.hualala.bi.framework.application.MBApplication;
import com.hualala.ui.widget.fresco.FrescoUtils;

public class App extends MBApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Gloading.debug(true);
        Gloading.initDefault(new GlobalAdapter());
        FrescoUtils.init(this);
    }
}
