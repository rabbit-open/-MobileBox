package com.hualala.data.executor;

import com.hualala.domain.executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by denglijun on 2017/11/30.
 */

public class UIThread implements PostExecutionThread {
    private UIThread() {
    }

    private static final class Holder {
        private static final UIThread INSTANCE = new UIThread();
    }

    public static UIThread getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
