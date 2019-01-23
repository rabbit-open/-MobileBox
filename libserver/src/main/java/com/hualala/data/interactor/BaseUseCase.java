package com.hualala.data.interactor;

import com.hualala.data.repository.ITerminalDataRepository;

/**
 * Created by denglijun on 2017/11/29.
 */
public abstract class BaseUseCase<T, Params> extends UseCase<T, Params> {
    protected final ITerminalDataRepository terminalDataRepository;

    protected BaseUseCase(BusinessContractor businessContractor) {
        super(businessContractor.getThreadExecutor(), businessContractor.getPostExecutionThread());
        terminalDataRepository = businessContractor.getTerminalDataRepository();
    }
}

