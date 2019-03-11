package com.hualala.ui.widget.fresco;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class FrescoBatchDownLoadManager {

    private List<String> mUrls;
    private List<String> mUrlsName;
    private String mSavePath;

    private MultiDownListener multiDownListener;

    private Handler handler=new Handler();

    public void start(ArrayList<String> urls,
                      ArrayList<String> names,
                      String filepath,
                      MultiDownListener multiDownListener) {

        mUrls = urls;
        mUrlsName = names;
        mSavePath = filepath;

        this.multiDownListener = multiDownListener;

        String path = mUrls.get(0);
        postImage(path, mUrlsName.get(0), 1);
    }


    private void postImage(final String path, String name, final int nextpos) {

        FrescoUtils.preLocalImageMemCache2(path, new SingleImagePreloadListener(path, mSavePath, name) {
            @Override
            public void onSuccess(int status, String savepath, String url) {
                if (status == 1) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (multiDownListener != null) {
                                multiDownListener.downProgress((int) (100f * nextpos / mUrls.size()));
                            }
                        }
                    });

                    if (nextpos == mUrls.size()) {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (multiDownListener != null) {
                                    multiDownListener.downFinished();
                                }
                            }
                        });
                    } else {
                        postImage(mUrls.get(nextpos), mUrlsName.get(nextpos), nextpos + 1);
                    }
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (multiDownListener != null) {
                                multiDownListener.downFail();
                            }
                        }
                    });

                }
            }
        });
    }


    public interface MultiDownListener {
        void downFinished();

        void downProgress(int progress);

        void downFail();
    }

}
