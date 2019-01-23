package com.hualala.data.general_config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by denglijun on 2017/11/30.
 */
@Getter
@Setter
@ToString
public class GeneralConfig {
    private CloudServerInfo cloudServerInfo = new CloudServerInfo();// 未使用
    private TerminalInfo terminalInfo = new TerminalInfo();// 未使用
}