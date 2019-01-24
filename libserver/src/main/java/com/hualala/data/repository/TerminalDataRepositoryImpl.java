package com.hualala.data.repository;

import com.hualala.data.entity.mapper.MBSimpleMapper;
import com.hualala.data.entity.resultcheck.MTPrecondition;
import com.hualala.data.net.CloudClient;
import com.hualala.domain.model.MVideo;
import com.hualala.domain.repository.ITerminalDataRepository;
import io.reactivex.Observable;

import java.util.List;

public class TerminalDataRepositoryImpl implements ITerminalDataRepository {
    private CloudClient cloudClient;

    public TerminalDataRepositoryImpl(CloudClient cloudClient) {
        this.cloudClient = cloudClient;
    }

    public void addHeader(String key, String value) {
        cloudClient.addHeader(key, value);
    }

    @Override
    public Observable<List<MVideo>> getVideoList() {
        return cloudClient.getCloudApi().getVideoList().map(MTPrecondition::checkSuccess).map(MBSimpleMapper::transform);
    }

}
