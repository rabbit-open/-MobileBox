package com.hualala.mobilebox.module.boot.view;

import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;

public interface IMainView {

    void updateData(MainViewData originData);

    interface IMainViewListener {

        void getMediaVideo();

        void getMediaAudio();

        void getMediaImage();
    }

}
