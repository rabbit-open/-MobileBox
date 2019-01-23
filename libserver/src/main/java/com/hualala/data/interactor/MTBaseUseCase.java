package com.hualala.data.interactor;


import com.hualala.data.general_config.TerminalInfo;

/**
 * Created by ggx
 */

public abstract class MTBaseUseCase<T, Params> extends BaseUseCase<T, Params> {
    protected TerminalInfo terminalInfo;

    public MTBaseUseCase(BusinessContractor businessContractor) {
        super(businessContractor);
        terminalInfo = businessContractor.getTerminalInfo();
    }
}
