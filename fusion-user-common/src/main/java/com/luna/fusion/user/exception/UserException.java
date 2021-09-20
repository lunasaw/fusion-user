package com.luna.fusion.user.exception;

import com.luna.common.exception.BaseException;
import com.luna.fusion.user.exception.constant.BizResultCode;

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
        return new UserException(BizResultCode.SITE_NOT_AUTH, BizResultCode.MSG_SITE_NOT_AUTH);
    }

    public static UserException isNonAdmin() {
        return new UserException(BizResultCode.SITE_NOT_ADMIN, BizResultCode.MSG_SITE_NOT_ADMIN);
    }

    public static UserException userMarkNotALlow() {
        return new UserException(BizResultCode.WRONG_USER_MARK_NOT_ALLOW, BizResultCode.MSG_USER_MARK_NOT_ALLOW);
    }
}
