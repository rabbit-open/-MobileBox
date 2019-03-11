package com.hualala.ui.widget.fresco;

import java.io.File;

public interface OnCompressListener {
        void onSuccess(File file);
        void onError(Throwable e);
    }