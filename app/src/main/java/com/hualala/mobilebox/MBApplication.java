package com.hualala.mobilebox;

import android.app.Application;
import com.hualala.mobilebox.lib.context.MBContext;

public class MBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MBContext.initContext(this);
    }
}
