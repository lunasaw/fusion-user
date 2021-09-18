package com.luna.fusion.user.client;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.luna.common.dto.ResultDTO;
import com.luna.common.net.RestUtils;
import com.luna.fusion.user.constant.URLConstant;
import com.luna.fusion.user.req.TagReq;
import com.luna.fusion.user.vo.TagVO;
import com.luna.fusion.user.vo.UserTagVO;

import java.util.List;

/**
 * @author Iszychen@win10
 * @date 2020/2/24 13:30
 */
public class UserTagClient {
    private String host;

    public UserTagClient(String host) {
        this.host = host;
    }

    private static final String PATH_PREFIX =
        URLConstant.S + URLConstant.FUSION_USER + URLConstant.S + URLConstant.API + URLConstant.S;

    public ResultDTO<List<UserTagVO>> listTagByUser(String sessionKey, String site, TagReq tagReq) {
        String result = RestUtils.doPost(host, PATH_PREFIX + URLConstant.TAG_LIST, null,
            ImmutableMap.of("sessionKey", sessionKey, "site", site), JSON.toJSONString(tagReq));
        return JSON.parseObject(result, new TypeReference<ResultDTO<List<UserTagVO>>>() {});
    }

    public ResultDTO<List<TagVO>> listTag(String sessionKey, String site) {
        String result = RestUtils.doGet(host, PATH_PREFIX + URLConstant.TAG_LIST, null,
            ImmutableMap.of("sessionKey", sessionKey, "site", site));
        return JSON.parseObject(result, new TypeReference<ResultDTO<List<TagVO>>>() {});
    }

    public ResultDTO<Boolean> hasTag(String sessionKey, String site, TagVO tagVO) {
        String result = RestUtils.doPost(host, PATH_PREFIX + URLConstant.HAS_TAG, null,
            ImmutableMap.of("sessionKey", sessionKey, "site", site), JSON.toJSONString(tagVO));
        return JSON.parseObject(result, new TypeReference<ResultDTO<Boolean>>() {});
    }

    public ResultDTO<Void> addTag(String sessionKey, String site, TagVO tagVO) {
        String result = RestUtils.doPost(host, PATH_PREFIX + URLConstant.ADD_TAG, null,
            ImmutableMap.of("sessionKey", sessionKey, "site", site), JSON.toJSONString(tagVO));
        return JSON.parseObject(result, new TypeReference<ResultDTO<Void>>() {});
    }

    public ResultDTO<Void> removeTag(String sessionKey, String site, TagVO tagVO) {
        String result = RestUtils.doPost(host, PATH_PREFIX + URLConstant.REMOVE_TAG, null,
            ImmutableMap.of("sessionKey", sessionKey, "site", site), JSON.toJSONString(tagVO));
        return JSON.parseObject(result, new TypeReference<ResultDTO<Void>>() {});
    }

}
