package com.hualala.mobilebox.module.tv;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TvModel {
    public String name;
    public String url;

    public TvModel(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
