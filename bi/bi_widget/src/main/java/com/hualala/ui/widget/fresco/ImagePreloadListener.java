package com.hualala.ui.widget.fresco;

import android.graphics.Bitmap;

public interface ImagePreloadListener {
        void onLoadingSuccess(Bitmap bitmap);
        void onLoadingFailed();
    }