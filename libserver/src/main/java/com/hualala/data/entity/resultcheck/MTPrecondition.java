package com.hualala.data.entity.resultcheck;

import com.hualala.data.entity.reponse.BaseReponse;
import com.hualala.data.exception.DuplicateCallException;

public class MTPrecondition<T> {
    private static final String SUCCESS_CODE = "200";
    private static final String DUPLICATE_CALL_CODE = "2211200021";

    public static <T extends BaseReponse> T checkSuccess(T reponse) throws Exception {
        if (!SUCCESS_CODE.equals(reponse.getCode())) {
            if (DUPLICATE_CALL_CODE.equals(reponse.getCode())) {
                throw new DuplicateCallException(new Throwable(reponse.getMsg()));
            } else {
                throw new Exception(reponse.getMsg());
            }
        }
        return reponse;
    }
}
