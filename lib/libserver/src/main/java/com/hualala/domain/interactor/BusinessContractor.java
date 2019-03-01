package com.hualala.domain.interactor;

import com.hualala.domain.exception.CreateUseCaseFailedException;
import com.hualala.domain.executor.PostExecutionThread;
import com.hualala.domain.executor.ThreadExecutor;
import com.hualala.domain.config.GeneralConfig;
import com.hualala.domain.config.TerminalInfo;
import com.hualala.domain.repository.ITerminalDataRepository;

import java.lang.reflect.InvocationTargetException;

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

    public  GeneralConfig getGeneralConfig() {
        return generalConfig;
    }
}