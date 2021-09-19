package com.luna.fusion.user.exception;

import com.luna.common.exception.BaseException;

/**
 * 异常类
 * 
 * @author luna
 *
 */
public class UserException extends BaseException {

    public UserException(int code, String message) {
        super(code, message);
    }

    public static UserException isNonAuth() {
        return new UserException(1104, "site not auth");
    }

    public static UserException isNonAdmin() {
        return new UserException(1104, "site not auth");
    }
}
