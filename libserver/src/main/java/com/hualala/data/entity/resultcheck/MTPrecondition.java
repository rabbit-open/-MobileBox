package com.hualala.data.entity.resultcheck;

import com.hualala.data.entity.reponse.BaseReponse;
import com.hualala.domain.exception.ErrorCodeException;

public class MTPrecondition<T> {

    private static final String SUCCESS_CODE = "200";

    public static <T extends BaseReponse> T checkSuccess(T reponse) throws Exception {
        if (reponse == null || reponse.getCode() == null) {
            throw new Exception("response data exception");
        }
        if (!SUCCESS_CODE.equals(reponse.getCode())) {
            throw new ErrorCodeException(reponse.getMsg(), new Throwable(reponse.getMsg()), reponse.getCode(), reponse.getMsg());
        }
        return reponse;
    }
}
