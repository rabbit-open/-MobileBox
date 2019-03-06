package com.hualala.domain.repository;

import com.hualala.domain.model.MVideo;
import com.hualala.server.phone.ContactsBean;

import io.reactivex.Observable;

import java.util.List;

public interface ITerminalDataRepository {

    //更换地址
    void changeServerAddr(String serverAddr);

    // 添加header
    void addHeader(String key, String value);

    // 视频列表
    Observable<List<MVideo>> getVideoList(String format);

    Observable<List<ContactsBean>> getPhoneInfo();
}
