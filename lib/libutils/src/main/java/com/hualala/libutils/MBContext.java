package com.hualala.libutils;

import android.app.Application;
import android.content.Context;

public class MBContext {

    private static Application mInstance;

    public static void setContext(Application mInstance) {
        MBContext.mInstance = mInstance;
    }

    public static Context getContext() {
        return mInstance;
    }

    public static Application getInstance() {
        return mInstance;
    }
}
