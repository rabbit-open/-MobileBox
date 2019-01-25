package com.hualala.mobilebox;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;
import com.hualala.server.api.JobMonitorService;
import com.hualala.server.api.MBService;
import com.hualala.mobilebox.compat.JobService21Compcat;
import com.hualala.mobilebox.lib.context.MBContext;

public class MBApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MBContext.initContext(this);
        //启动服务
        startService(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobService21Compcat.scheduleJob(this, JobMonitorService.class);
        }
        MBBusinessContractor.initBusinessContractor();
    }


    private static void startService(Context context) {
        Intent intent = new Intent(context, MBService.class);
        intent.putExtra("startType", 1);
        ContextCompat.startForegroundService(context, intent);
    }
}
