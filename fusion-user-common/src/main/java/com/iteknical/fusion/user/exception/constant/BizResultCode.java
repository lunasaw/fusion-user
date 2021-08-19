package com.iteknical.fusion.user.exception.constant;

import java.util.Locale;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * @author Tony
 */
public interface BizResultCode {

    /** 用户未登录（不存在的sessionKey） */
    int                              USER_NOT_SIGN_IN                = 1101;
    String                           MSG_USER_NOT_SIGN_IN            = "user not sign in";

    /** 用户名或密码错误 */
    int                              WRONG_USER_MARK_OR_PASSWORD     = 1102;
    String                           MSG_WRONG_USER_MARK_OR_PASSWORD = "wrong user mark or password";

    /** 站点未激活 */
    int                              SITE_NOT_ACTIVATED              = 1103;
    String                           MSG_SITE_NOT_ACTIVATED          = "site not activated";

    Map<Locale, Map<String, String>> TRANSLATION_MAP                 =
        ImmutableMap.<Locale, Map<String, String>>builder()
            .put(Locale.SIMPLIFIED_CHINESE,
                ImmutableMap.<String, String>builder()
                    .put(MSG_USER_NOT_SIGN_IN, "用户未登录")
                    .put(MSG_WRONG_USER_MARK_OR_PASSWORD, "用户名或密码错误")
                    .put(MSG_SITE_NOT_ACTIVATED, "站点未激活")
                    .build())
            .build();
}
