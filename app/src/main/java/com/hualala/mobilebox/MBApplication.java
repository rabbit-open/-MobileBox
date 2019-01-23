package com.hualala.mobilebox;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import com.hualala.data.executor.UIThread;
import com.hualala.data.repository.TerminalDefaultBusinessContractor;
import com.hualala.data.general_config.GeneralConfig;
import com.hualala.libserver.JobMonitorService;
import com.hualala.libserver.MBService;
import com.hualala.mobilebox.compat.JobService21Compcat;
import com.hualala.mobilebox.lib.context.MBContext;
import lombok.Getter;

public class MBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MBContext.initContext(this);
        //启动服务
        startService(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobService21Compcat.scheduleJob(this, JobMonitorService.class);
        }
        //API

    }

    @Getter
    private static TerminalDefaultBusinessContractor businessContractor;

    public static void initBusinessContractor() {
        GeneralConfig generalConfig = new GeneralConfig();
        generalConfig.getCloudServerInfo().setBaseApiUrl("http://127.0.0.1");
        businessContractor = new TerminalDefaultBusinessContractor(generalConfig, UIThread.getInstance(), null);
    }

    private static void startService(Context context) {
        Intent intent = new Intent(context, MBService.class);
        intent.putExtra("startType", 1);
        ContextCompat.startForegroundService(context, intent);
    }
}
