package com.luna.fusion.user.constant;

/**
 * @author Luna
 * @Description URL常量
 * @date 2019年8月31日 下午4:05:10
 */
public interface URLConstant {

    /**
     * 分隔符
     */
    String S                          = "/";
    /**
     * 应用路径
     */
    String FUSION_USER                = "fusion-user";

    /**
     * 登录
     */
    String LOGIN                      = "login";
    /**
     * 注册
     */
    String REGISTER                   = "register";
    /**
     * 重置密码
     */
    String RESET_PASSWORD             = "resetPassword";

    /**
     * 用户中心
     */
    String USER                       = "user";
    /**
     * 用户中心首页
     */
    String INDEX                      = "index";

    /**
     * rest
     */
    String API                        = "api";
    /**
     * sessionKey换userId
     */
    String GET_USER_ID_BY_SESSION_KEY = "getUserIdBySessionKey";

    /**
     * 检查用户是否存在
     */
    String CHECK_USER_EXIST           = "checkUserExist";
    /**
     * userId换userDO
     */
    String GET_USER_DO_BY_USER_ID     = "getDOByUserId";
    /**
     * sessionKey换userDTO
     */
    String GET_USER_DO_BY_SESSION_KEY = "getUserDOBySessionKey";

    /**
     * 查看所有标
     */
    String TAG_LIST                   = "listTag";

    /**
     * 检验标是否存在
     */
    String HAS_TAG                    = "hasTag";
    /**
     * 加标
     */
    String ADD_TAG                    = "addTag";
    /**
     * 移除标
     */
    String REMOVE_TAG                 = "removeTag";
}
