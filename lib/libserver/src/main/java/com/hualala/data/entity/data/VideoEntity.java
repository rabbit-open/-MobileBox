package com.hualala.data.entity.data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VideoEntity {
    public String path;
    public String thumbPath;
    public String name;
    public long duration;
    public long size;
    public int width;
    public int height;
}
