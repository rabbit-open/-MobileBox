package com.hualala.data.executor;

import android.support.annotation.NonNull;
import com.hualala.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultExecutor implements ThreadExecutor {
    private final ThreadPoolExecutor mThreadPoolExecutor;
    private static final int DEFAULT_CORE_POOL_SIZE = 3;
    private static final int DEFAULT_MAX_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 10;

    public DefaultExecutor() {
        this(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE, DEFAULT_KEEP_ALIVE_TIME);
    }

    public DefaultExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mThreadPoolExecutor.execute(command);
    }

    private class JobThreadFactory implements ThreadFactory {
        private int mCounter = 0;

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "jobfactory_" + mCounter++);
        }
    }
}
