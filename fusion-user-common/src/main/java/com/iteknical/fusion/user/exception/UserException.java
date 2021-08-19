package com.iteknical.fusion.user.exception;

import com.iteknical.common.utils.exception.BaseException;

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
