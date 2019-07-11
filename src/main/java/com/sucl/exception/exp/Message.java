package com.sucl.exception.exp;

import com.sun.org.apache.bcel.internal.classfile.Code;

/**
 * @author sucl
 * @since 2019/7/10
 */
public interface Message {

    Code getCode();

    String getInfo();

    Object getResult();

    enum  Code{
        ERROR,SUCCESS;
    }
}
