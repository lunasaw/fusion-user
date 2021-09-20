package com.luna.fusion.user.exception.constant;

import java.util.Locale;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * @author Luna
 */
public interface BizResultCode {

    /** 用户未登录（不存在的sessionKey） */
    int                              USER_NOT_SIGN_IN                = 1101;
    String                           MSG_USER_NOT_SIGN_IN            = "user not sign in";

    /** 用户名或密码错误 */
    int                              WRONG_USER_MARK_OR_PASSWORD     = 1102;
    String                           MSG_WRONG_USER_MARK_OR_PASSWORD = "wrong user mark or password";

    /** 不是一个合法的手机号或者邮箱地址 */
    int                              WRONG_USER_MARK_NOT_ALLOW       = 1102;
    String                           MSG_USER_MARK_NOT_ALLOW         = "wrong user mark not allow";

    /** 站点未激活 */
    int                              SITE_NOT_ACTIVATED              = 1103;
    String                           MSG_SITE_NOT_ACTIVATED          = "site not activated";

    /** 权限未认证 */
    int                              SITE_NOT_AUTH                   = 1104;
    String                           MSG_SITE_NOT_AUTH               = "site not auth";

    /** 不是管理员 */
    int                              SITE_NOT_ADMIN                  = 1105;
    String                           MSG_SITE_NOT_ADMIN              = "site not admin";

    Map<Locale, Map<String, String>> TRANSLATION_MAP                 =
        ImmutableMap.<Locale, Map<String, String>>builder()
            .put(Locale.SIMPLIFIED_CHINESE,
                ImmutableMap.<String, String>builder()
                    .put(MSG_USER_NOT_SIGN_IN, "用户未登录")
                    .put(MSG_WRONG_USER_MARK_OR_PASSWORD, "用户名或密码错误")
                    .put(MSG_SITE_NOT_ACTIVATED, "站点未激活")
                    .put(MSG_SITE_NOT_AUTH, "权限未认证")
                    .put(MSG_SITE_NOT_ADMIN, "不是管理员")
                    .put(MSG_USER_MARK_NOT_ALLOW, "不是一个合法的手机号或者邮箱地址")
                    .build())
            .build();
}
