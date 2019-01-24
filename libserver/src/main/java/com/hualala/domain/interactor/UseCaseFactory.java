package com.hualala.domain.interactor;

public interface UseCaseFactory {
    public <T extends BaseUseCase> T create(Class<T> clazz);
}
