package com.hualala.libcommonui.banner;

import java.io.Serializable;

public class MYBannerData implements Serializable {

    private static final long serialVersionUID = 217350830028803104L;

    public enum BannerType {
        none
    }

    public MYBannerData(String image) {
        this.image = image;
    }

    public String image;

}