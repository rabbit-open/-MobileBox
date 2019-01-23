package com.hualala.data.repository;

import com.hualala.data.entity.reponse.MBVideoListResponse;
import io.reactivex.Observable;

public interface ITerminalDataRepository {
    // 添加header
    void addHeader(String key, String value);

    // 视频列表
    Observable<MBVideoListResponse> getVideoList();

}
