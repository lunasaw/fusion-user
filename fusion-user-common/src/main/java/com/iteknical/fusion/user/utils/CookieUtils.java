package com.iteknical.fusion.user.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.iteknical.fusion.user.exception.UserException;
import com.iteknical.fusion.user.exception.constant.BizResultCode;

/**
 * @author Luna
 */
public class CookieUtils {
    /** session key名字 */
    public static final String SESSION_KEY_NAME = "sessionKey";

    public static String getSessionKeyFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SESSION_KEY_NAME)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getOneSessionKey(HttpServletRequest request) {
        return getOneSessionKey(null, request);
    }

    /**
     * 优先使用paramSessionKey
     * 
     * @param request
     * @param paramSessionKey
     * @return
     */
    public static String getOneSessionKey(String paramSessionKey, HttpServletRequest request) {
        if (StringUtils.isNotBlank(paramSessionKey)) {
            return paramSessionKey;
        }

        String sessionKey = getSessionKeyFromRequest(request);
        if (StringUtils.isNotBlank(sessionKey)) {
            return sessionKey;
        }
        throw new UserException(BizResultCode.USER_NOT_SIGN_IN, BizResultCode.MSG_USER_NOT_SIGN_IN);
    }
}
