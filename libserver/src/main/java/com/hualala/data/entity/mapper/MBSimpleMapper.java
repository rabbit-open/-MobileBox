package com.hualala.data.entity.mapper;

import com.hualala.data.entity.reponse.BaseReponse;

public class MBSimpleMapper {

    public static <T> T transform(BaseReponse<T> reponse) {
        return reponse.getData();
    }

}
