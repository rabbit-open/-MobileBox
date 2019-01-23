package com.hualala.data.repository;

import com.hualala.data.entity.reponse.MBVideoListResponse;
import com.hualala.data.entity.resultcheck.MTPrecondition;
import com.hualala.data.net.CloudClient;
import io.reactivex.Observable;

public class TerminalDataRepositoryImpl implements ITerminalDataRepository {
    private CloudClient cloudClient;

    public TerminalDataRepositoryImpl(CloudClient cloudClient) {
        this.cloudClient = cloudClient;
    }

    public void addHeader(String key, String value) {
        cloudClient.addHeader(key, value);
    }

    @Override
    public Observable<MBVideoListResponse> getVideoList() {
        return cloudClient.getCloudApi().getVideoList().map(MTPrecondition::checkSuccess);
    }

}
