package com.hualala.data.executor;

import android.support.annotation.NonNull;
import com.hualala.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor implements ThreadExecutor {
    private final ThreadPoolExecutor mThreadPoolExecutor;

    private static final long TASK_TIMEOUT = 60;
    private static final int QUEUE_SIZE = 10;

    public SingleThreadExecutor() {
        mThreadPoolExecutor = new ThreadPoolExecutor(1, 1, TASK_TIMEOUT, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(QUEUE_SIZE), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mThreadPoolExecutor.execute(command);
    }
}
