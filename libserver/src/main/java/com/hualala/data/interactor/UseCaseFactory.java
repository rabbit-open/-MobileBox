package com.hualala.data.interactor;

/**
 * Created by denglijun on 2017/11/30.
 */

public interface UseCaseFactory {
    public <T extends BaseUseCase> T create(Class<T> clazz);
}
