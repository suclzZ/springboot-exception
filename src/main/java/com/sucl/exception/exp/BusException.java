package com.sucl.exception.exp;

import org.springframework.core.NestedRuntimeException;

/**
 * @author sucl
 * @since 2019/7/10
 */
public class BusException extends NestedRuntimeException implements Message{

    public BusException(String msg) {
        super(msg);
    }

    public BusException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public Code getCode() {
        return Code.ERROR;
    }

    @Override
    public String getInfo() {
        return getMessage();
    }

    @Override
    public Object getResult() {
        return null;
    }
}
