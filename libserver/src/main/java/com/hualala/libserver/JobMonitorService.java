package com.hualala.libserver;

import android.app.ActivityManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.ContentValues.TAG;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobMonitorService extends JobService {

    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");
        if (!isMyServiceRunning(MBService.class)) {
            Intent intent = new Intent(getApplicationContext(), MBService.class);
            intent.putExtra("startType", 1);
            ContextCompat.startForegroundService(getApplicationContext(), intent);
        }
        return false;
    }

    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob");
        return false;
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}