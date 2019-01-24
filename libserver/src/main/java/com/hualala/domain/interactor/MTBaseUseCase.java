package com.hualala.domain.interactor;


import com.hualala.domain.config.TerminalInfo;

public abstract class MTBaseUseCase<T, Params> extends BaseUseCase<T, Params> {
    protected TerminalInfo terminalInfo;

    public MTBaseUseCase(BusinessContractor businessContractor) {
        super(businessContractor);
        terminalInfo = businessContractor.getTerminalInfo();
    }
}
