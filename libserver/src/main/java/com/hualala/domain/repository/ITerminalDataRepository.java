package com.hualala.domain.repository;

import com.hualala.domain.model.MVideo;
import io.reactivex.Observable;

import java.util.List;

public interface ITerminalDataRepository {
    // 添加header
    void addHeader(String key, String value);

    // 视频列表
    Observable<List<MVideo>> getVideoList();

}
