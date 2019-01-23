package com.hualala.data.entity.reponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class BaseReponse<T> {
    protected String code;

    protected String msg;

    protected String traceID;

    protected T data;  // 可以是{}也可以是[] 也可以是""
}
