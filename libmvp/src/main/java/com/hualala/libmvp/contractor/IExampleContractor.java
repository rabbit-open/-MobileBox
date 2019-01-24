package com.hualala.libmvp.contractor;

public interface IExampleContractor {
    public interface IExampleView {
        void showName();
    }

    public interface IExamplePresenter {
        void getList();
    }
}
