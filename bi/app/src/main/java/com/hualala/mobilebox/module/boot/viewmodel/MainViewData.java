package com.hualala.mobilebox.module.boot.viewmodel;

import com.hualala.domain.model.MVideo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainViewData {

    public List<MVideo> videoList;
    public int type;// 1,2,3

    public boolean isSuccess;

    @Setter
    @Getter
    public static class Status {
        public int status; // 0-数据正常 1- 空数据 2-网络异常 3-业务错误
        public int pageNum; //当前页数
        public int errorCode;//业务错误信息
        public int errorMsg;//业务错误信息
    }
}