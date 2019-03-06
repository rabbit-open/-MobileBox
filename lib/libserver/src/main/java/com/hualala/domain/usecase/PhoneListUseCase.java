package com.hualala.domain.usecase;

import com.hualala.domain.interactor.BusinessContractor;
import com.hualala.domain.interactor.MTBaseUseCase;
import com.hualala.domain.model.MVideo;
import com.hualala.server.phone.ContactsBean;

import java.util.List;

import io.reactivex.Observable;

public class PhoneListUseCase extends MTBaseUseCase<List<ContactsBean>, Void> {

    public PhoneListUseCase(BusinessContractor businessContractor) {
        super(businessContractor);
    }

    @Override
    protected Observable<List<ContactsBean>> buildUseCaseObservable(Void params) {
        return terminalDataRepository.getPhoneInfo();
    }

}
