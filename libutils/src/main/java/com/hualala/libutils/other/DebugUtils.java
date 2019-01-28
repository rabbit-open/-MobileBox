package com.hualala.libutils.other;

import android.content.pm.ApplicationInfo;
import com.hualala.libutils.MBContext;

public class DebugUtils {

    public static boolean isDebug() {
        ApplicationInfo applicationInfo = MBContext.getContext().getApplicationInfo();
        return applicationInfo != null
                && (applicationInfo.flags &
                ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

}
