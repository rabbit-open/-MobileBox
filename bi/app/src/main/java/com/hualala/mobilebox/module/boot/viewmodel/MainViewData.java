package com.hualala.mobilebox.module.boot.viewmodel;

import com.hualala.domain.model.MVideo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MainViewData {

    public List<MVideo> videoList;
    public int type;// 1,2,3

    public boolean isSuccess;

}