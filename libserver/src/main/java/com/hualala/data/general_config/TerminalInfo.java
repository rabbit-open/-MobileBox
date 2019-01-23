package com.hualala.data.general_config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ggx
 */

@Getter
@Setter
@ToString
public class TerminalInfo {
    private String snCode;
    private String token;
    private String versionCode;
    private String versionName;
}
