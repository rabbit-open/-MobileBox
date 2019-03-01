package com.hualala.domain.usecase;

import com.hualala.domain.interactor.BusinessContractor;
import com.hualala.domain.interactor.MTBaseUseCase;
import com.hualala.domain.model.MVideo;
import io.reactivex.Observable;

import java.util.List;

public class VideoListUseCase extends MTBaseUseCase<List<MVideo>, String> {

    public VideoListUseCase(BusinessContractor businessContractor) {
        super(businessContractor);
    }

    @Override
    protected Observable<List<MVideo>> buildUseCaseObservable(String params) {
        return terminalDataRepository.getVideoList(params);
    }

}
