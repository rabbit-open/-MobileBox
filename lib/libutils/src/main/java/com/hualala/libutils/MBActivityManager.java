package com.hualala.libutils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MBActivityManager {

    private Activity app_activity = null;
    private Activity pause_activity = null;
    private List<String> activities = new ArrayList<>();

    public void initGlobeActivity(Application app, final Application.ActivityLifecycleCallbacks ex) {
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                app_activity = activity;
                activities.add(activity.getPackageName() + "." + activity.getLocalClassName());
                if (ex != null) {
                    ex.onActivityCreated(activity, savedInstanceState);
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activities.remove(activity.getPackageName() + "." + activity.getLocalClassName());
                if (ex != null) {
                    ex.onActivityDestroyed(activity);
                }
            }

            /** Unused implementation **/
            @Override
            public void onActivityStarted(Activity activity) {
                app_activity = activity;
                if (ex != null) {
                    ex.onActivityStarted(activity);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                app_activity = activity;
                if (ex != null) {
                    ex.onActivityResumed(activity);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
                pause_activity = activity;
                if (ex != null) {
                    ex.onActivityPaused(activity);
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {
                pause_activity = activity;
                if (ex != null) {
                    ex.onActivityStopped(activity);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                if (ex != null) {
                    ex.onActivitySaveInstanceState(activity, outState);
                }
            }
        });
    }

    /**
     * 公开方法，外部可通过 MyApplication.getInstance().getCurrentActivity() 获取到当前最上层的activity
     */
    public Activity getCurrentActivity() {
        return app_activity;
    }

    public Activity getLastActivity() {
        return pause_activity;
    }

    private static MBActivityManager mtActivityManager = new MBActivityManager();

    public static MBActivityManager getMtActivityManager() {
        return mtActivityManager;
    }
}
