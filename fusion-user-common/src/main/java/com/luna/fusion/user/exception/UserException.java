package com.luna.fusion.user.exception;

import com.luna.common.exception.BaseException;

/**
 * 异常类
 * 
 * @author 15272
 *
 */
public class UserException extends BaseException {

    public UserException(int code, String message) {
        super(code, message);
    }
}
