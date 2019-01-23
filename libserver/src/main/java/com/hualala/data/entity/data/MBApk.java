package com.hualala.data.entity.data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MBApk {
    public String url;//资源地址
    public int format;//资源格式
    public int size;//资源大小
    public int time;//资源时长
    public String name;//资源名称
}
