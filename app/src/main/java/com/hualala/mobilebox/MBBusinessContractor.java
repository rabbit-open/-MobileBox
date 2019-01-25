package com.hualala.mobilebox;


import com.hualala.data.executor.UIThread;
import com.hualala.data.repository.TerminalDefaultBusinessContractor;
import com.hualala.domain.config.GeneralConfig;
import com.hualala.domain.interactor.BusinessContractor;

public class MBBusinessContractor {

    private static BusinessContractor businessContractor;
    private static GeneralConfig generalConfig;

    public static BusinessContractor getBusinessContractor() {
        return businessContractor;
    }

    public static GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    public static void initBusinessContractor() {
        GeneralConfig generalConfig = new GeneralConfig();
        businessContractor = new TerminalDefaultBusinessContractor(generalConfig, UIThread.getInstance(), null,"http://127.0.0.1:80/");
    }

}
