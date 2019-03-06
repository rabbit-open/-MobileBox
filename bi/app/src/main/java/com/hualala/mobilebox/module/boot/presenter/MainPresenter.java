package com.hualala.mobilebox.module.boot.presenter;

import android.arch.lifecycle.ViewModelProviders;
import android.view.View;
import com.hualala.domain.interactor.DefaultObserver;
import com.hualala.domain.model.MVideo;
import com.hualala.domain.usecase.VideoListUseCase;
import com.hualala.bi.framework.application.MBBusinessContractor;
import com.hualala.bi.framework.base.BasePresenter;
import com.hualala.bi.framework.base.ErrorUtils;
import com.hualala.bi.framework.base.ViewDelegate;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewModel;

import java.util.List;

public class MainPresenter extends BasePresenter<ViewDelegate<View>> implements IMainView.IMainViewListener {

    private MainViewModel viewModel;

    public MainPresenter(ViewDelegate<View> mView) {
        super(mView);
        viewModel = ViewModelProviders.of(mView.getFragmentActivity()).get(MainViewModel.class);
    }

    @Override
    public void getMediaVideo() {
        VideoListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<MVideo>>() {

            MainViewData mainViewData;

            @Override
            public void onNext(List<MVideo> mVideos) {
                mainViewData = new MainViewData();
                mainViewData.setType(2);
                mainViewData.setVideoList(mVideos);
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
                ErrorUtils.handleError(getView(), throwable);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                viewModel.getData().postValue(mainViewData);
            }

        }, "mp4");
    }

    @Override
    public void getMediaAudio() {
        VideoListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<MVideo>>() {

            MainViewData mainViewData;

            @Override
            public void onNext(List<MVideo> mVideos) {
                mainViewData = new MainViewData();
                mainViewData.setType(3);
                mainViewData.setVideoList(mVideos);
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
                ErrorUtils.handleError(getView(), throwable);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                viewModel.getData().postValue(mainViewData);
            }

        }, "mp3");
    }

    @Override
    public void getMediaImage() {
        VideoListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<MVideo>>() {

            MainViewData mainViewData;

            @Override
            public void onNext(List<MVideo> mVideos) {
                mainViewData = new MainViewData();
                mainViewData.setType(1);
                mainViewData.setVideoList(mVideos);
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
                ErrorUtils.handleError(getView(), throwable);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                viewModel.getData().postValue(mainViewData);
            }

        }, "jpg");
    }

}
