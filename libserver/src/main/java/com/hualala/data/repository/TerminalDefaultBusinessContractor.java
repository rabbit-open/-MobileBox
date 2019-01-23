package com.hualala.data.repository;

import com.hualala.data.executor.DefaultExecutor;
import com.hualala.data.net.CloudClient;
import com.hualala.data.executor.PostExecutionThread;
import com.hualala.data.executor.ThreadExecutor;
import com.hualala.data.general_config.GeneralConfig;
import com.hualala.data.interactor.BusinessContractor;

public class TerminalDefaultBusinessContractor extends BusinessContractor {
    private ITerminalDataRepository iTerminalDataRepository;
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;

    public TerminalDefaultBusinessContractor(GeneralConfig generalConfig, PostExecutionThread postExecutionThread) {
        this(generalConfig, postExecutionThread, null);
    }

    public TerminalDefaultBusinessContractor(GeneralConfig generalConfig, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {
        super(generalConfig);
        iTerminalDataRepository = new TerminalDataRepositoryImpl(new CloudClient(new CloudClient.ClientConfig(generalConfig.getCloudServerInfo().getBaseApiUrl(), CloudClient.TRACE_ID_PRE_TERMINAL)));
        this.postExecutionThread = postExecutionThread;
        if (threadExecutor != null) {
            this.threadExecutor = threadExecutor;
        } else {
            this.threadExecutor = new DefaultExecutor();
        }
    }

    public TerminalDefaultBusinessContractor(GeneralConfig generalConfig, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, String url) {
        super(generalConfig);
        iTerminalDataRepository = new TerminalDataRepositoryImpl(new CloudClient(new CloudClient.ClientConfig(url, CloudClient.TRACE_ID_PRE_TERMINAL)));
        this.postExecutionThread = postExecutionThread;
        if (threadExecutor != null) {
            this.threadExecutor = threadExecutor;
        } else {
            this.threadExecutor = new DefaultExecutor();
        }
    }


    @Override
    public ITerminalDataRepository getTerminalDataRepository() {
        return iTerminalDataRepository;
    }

    @Override
    public ThreadExecutor getThreadExecutor() {
        return threadExecutor;
    }

    @Override
    public PostExecutionThread getPostExecutionThread() {
        return postExecutionThread;
    }
}
