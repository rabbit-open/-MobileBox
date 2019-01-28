package com.hualala.mobilebox.module.boot.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import com.hualala.domain.interactor.DefaultObserver;
import com.hualala.domain.model.MVideo;
import com.hualala.domain.usecase.VideoListUseCase;
import com.hualala.mobilebox.MBBusinessContractor;
import com.hualala.mobilebox.base.BasePresenter;
import com.hualala.mobilebox.base.ErrorUtils;
import com.hualala.mobilebox.module.boot.view.IMainView;
import com.hualala.mobilebox.module.boot.view.MainView;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewData;
import com.hualala.mobilebox.module.boot.viewmodel.MainViewModel;

import java.util.List;

public class MainContractorPresenter extends BasePresenter<MainView> implements IMainView.IMainViewListener {

    private MainViewModel viewModel = new MainViewModel();

    public MainContractorPresenter(LifecycleOwner lifecycleOwner) {
        viewModel.getData().observe(lifecycleOwner, new Observer<MainViewData>() {
            @Override
            public void onChanged(@Nullable MainViewData mainViewData) {
                mView.updateData(mainViewData);
            }
        });
    }

    @Override
    public void getMediaVideo() {
        VideoListUseCase videoListUseCase = MBBusinessContractor.getBusinessContractor().create(VideoListUseCase.class);
        videoListUseCase.execute(new DefaultObserver<List<MVideo>>() {

            MainViewData mainViewData;

            @Override
            public void onNext(List<MVideo> mVideos) {
                mainViewData = new MainViewData();
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
