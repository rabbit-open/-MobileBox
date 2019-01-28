package com.hualala.domain.config;

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
    private CloudServerInfo cloudServerInfo = new CloudServerInfo();
    private TerminalInfo terminalInfo = new TerminalInfo();
}