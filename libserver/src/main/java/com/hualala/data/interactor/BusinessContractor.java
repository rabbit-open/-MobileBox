package com.hualala.data.interactor;

import com.hualala.data.exception.CreateUseCaseFailedException;
import com.hualala.data.executor.PostExecutionThread;
import com.hualala.data.executor.ThreadExecutor;
import com.hualala.data.general_config.GeneralConfig;
import com.hualala.data.general_config.TerminalInfo;
import com.hualala.data.repository.ITerminalDataRepository;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by denglijun on 2017/11/30.
 */

public abstract class BusinessContractor implements UseCaseFactory {
    protected GeneralConfig generalConfig;

    public BusinessContractor(GeneralConfig generalConfig) {
        if (generalConfig != null) {
            this.generalConfig = generalConfig;
        } else {
            this.generalConfig = new GeneralConfig();
        }
    }

    public abstract ITerminalDataRepository getTerminalDataRepository();

    public abstract ThreadExecutor getThreadExecutor();

    public abstract PostExecutionThread getPostExecutionThread();

    @Override
    public <T extends BaseUseCase> T create(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor(BusinessContractor.class).newInstance(this);
        } catch (NoSuchMethodException e) {
            throw new CreateUseCaseFailedException(
                    clazz.getName() + " has no constructor with Context", e);
        } catch (InstantiationException e) {
            throw new CreateUseCaseFailedException(
                    "Failed to instantiation " + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            throw new CreateUseCaseFailedException(
                    "Failed to invoke constructor of " + clazz.getName(), e);
        } catch (IllegalAccessException e) {
            throw new CreateUseCaseFailedException(
                    "Failed to access " + clazz.getName(), e);
        }
    }

    public TerminalInfo getTerminalInfo() {
        return generalConfig.getTerminalInfo();
    }

}