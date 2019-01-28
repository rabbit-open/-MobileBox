//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hualala.libutils.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorManager {
    public ThreadExecutorManager() {
    }

    public static ExecutorService getSinglePool() {
        return ThreadExecutorManager.PoolHolder.mPool;
    }

    public static void execute(Runnable callback) {
        getSinglePool().execute(callback);
    }

    private static class PoolHolder {
        private static final ExecutorService mPool = Executors.newSingleThreadExecutor();

        private PoolHolder() {
        }
    }
}
