package com.hualala.bi.framework.application;


import com.hualala.data.executor.UIThread;
import com.hualala.data.repository.TerminalDefaultBusinessContractor;
import com.hualala.domain.config.CloudServerInfo;
import com.hualala.domain.config.GeneralConfig;
import com.hualala.domain.interactor.BusinessContractor;
import com.hualala.libutils.MBContext;
import com.hualala.libutils.wifi.WifiUtils;

public class MBBusinessContractor {

    private static BusinessContractor businessContractor;

    public static BusinessContractor getBusinessContractor() {
        return businessContractor;
    }

    public static void initBusinessContractor() {
        GeneralConfig generalConfig = new GeneralConfig();
        CloudServerInfo cloudServerInfo = new CloudServerInfo();
        cloudServerInfo.setBaseApiUrl("http://0.0.0.0:8888/");
        generalConfig.setCloudServerInfo(cloudServerInfo);
        businessContractor = new TerminalDefaultBusinessContractor(generalConfig, UIThread.getInstance(), null);
    }

    public static String getFileBaseUrl() {
        String url = getBusinessContractor().getGeneralConfig().getCloudServerInfo().getBaseApiUrl();
        String ip = WifiUtils.getWifiIp(MBContext.getContext());
        if (url.contains("0.0.0.0:8888") || (ip != null && url.contains(ip))) {
            return "file://";
        }

        return MBBusinessContractor.getBusinessContractor()
                .getGeneralConfig().getCloudServerInfo().getBaseApiUrl() + "files/";
    }

    public static String getShareFileBaseUrl() {
        return MBBusinessContractor.getBusinessContractor()
                .getGeneralConfig().getCloudServerInfo().getBaseApiUrl() + "files/";
    }

    public static String getDeviceBaseUrl() {
        String ip = WifiUtils.getWifiIp(MBContext.getContext());
        return ip != null ? "http://" + ip + ":8888/" : "http://0.0.0.0:8888/";
    }

}
