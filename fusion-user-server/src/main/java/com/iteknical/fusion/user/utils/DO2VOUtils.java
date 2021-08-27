package com.iteknical.fusion.user.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.iteknical.fusion.user.entity.TagDO;
import com.iteknical.fusion.user.entity.UserDO;
import com.iteknical.fusion.user.vo.TagVO;
import com.iteknical.fusion.user.vo.UserTagVO;

import java.util.Set;

/**
 * @author chenzhangyue@weidian.com
 * 2021/8/27
 */
public class DO2VOUtils {

    public static TagVO tagDO2TagVO(TagDO tagDO, String userMask) {
        if (tagDO == null) {
            return null;
        }
        TagVO tagVO = new TagVO();
        tagVO.setName(tagDO.getName());
        return tagVO;
    }

    public static UserTagVO userDO2UserTagVO(UserDO userDO, String tagName) {
        if (userDO == null) {
            return null;
        }
        UserTagVO userTagVO = new UserTagVO();
        userTagVO.setId(userDO.getId());
        userTagVO.setCreateTime(userDO.getCreateTime());
        userTagVO.setModifiedTime(userDO.getModifiedTime());
        userTagVO.setEmail(userDO.getEmail());
        userTagVO.setMobile(userDO.getMobile());
        userTagVO.setSiteSet(JSON.parseObject(userDO.getSites(), new TypeReference<Set<String>>() {}));
        userTagVO.setTagName(tagName);
        return userTagVO;
    }
}
