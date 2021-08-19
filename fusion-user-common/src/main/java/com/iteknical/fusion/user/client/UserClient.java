package com.iteknical.fusion.user.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.utils.RestUtils;
import com.iteknical.fusion.user.constant.URLConstant;
import com.iteknical.fusion.user.entity.UserDO;
import com.iteknical.fusion.user.vo.LoginVO;

/**
 * @author Tony
 */
public class UserClient {
    private String host;

    public UserClient(String host) {
        this.host = host;
    }

    private static final String PATH_PREFIX =
        URLConstant.S + URLConstant.FUSION_USER + URLConstant.S + URLConstant.API + URLConstant.S;

    public ResultDTO<UserDO> getUserDOBySessionKey(String sessionKey, String site) {
        String result = RestUtils.doGet(host, PATH_PREFIX + URLConstant.GET_USER_DO_BY_SESSION_KEY, null,
            ImmutableMap.of("sessionKey", sessionKey, "site", site));
        return JSON.parseObject(result, new TypeReference<ResultDTO<UserDO>>() {});
    }

    public ResultDTO<Long> getUserIdBySessionKey(String sessionKey, String site) {
        String result = RestUtils.doGet(host, PATH_PREFIX + URLConstant.GET_USER_ID_BY_SESSION_KEY, null,
            ImmutableMap.of("sessionKey", sessionKey, "site", site));
        return JSON.parseObject(result, new TypeReference<ResultDTO<Long>>() {});
    }

    public ResultDTO<String> login(LoginVO loginVO) {
        String result = RestUtils.doPost(host, PATH_PREFIX + URLConstant.LOGIN, null, null, JSON.toJSONString(loginVO));
        return JSON.parseObject(result, new TypeReference<ResultDTO<String>>() {});
    }

}