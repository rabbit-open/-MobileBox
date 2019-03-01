package com.hualala.mobilebox;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.hualala.libutils.MBContext;
import com.hualala.libutils.compat.JobService21Compcat;
import com.hualala.mobilebox.utils.FileUtils;
import com.hualala.server.api.JobMonitorService;
import com.hualala.server.api.MBService;

public class MBApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MBContext.setContext(this);
        //启动服务
        startService(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobService21Compcat.scheduleJob(this, JobMonitorService.class);
        }
        MBBusinessContractor.initBusinessContractor();

        Fresco.initialize(this, ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryPath(FileUtils.getAppFolder())
                        .setBaseDirectoryName("images")
                        .setMaxCacheSize(1000 * ByteConstants.MB)
                        .setMaxCacheSizeOnLowDiskSpace(50 * ByteConstants.MB)
                        .setMaxCacheSizeOnVeryLowDiskSpace(10 * ByteConstants.MB).build())
                .setDownsampleEnabled(true).build());
    }

    private static void startService(Context context) {
        Intent intent = new Intent(context, MBService.class);
        intent.putExtra("startType", 1);
        ContextCompat.startForegroundService(context, intent);
    }
}
