package com.luna.fusion.user.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.luna.fusion.user.entity.TagDO;
import com.luna.fusion.user.entity.UserDO;
import com.luna.fusion.user.vo.TagVO;
import com.luna.fusion.user.vo.UserTagVO;

import java.util.Date;
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

    public static UserTagVO userDO2UserTagVO(UserDO userDO, Date data, String tagName) {
        if (userDO == null) {
            return null;
        }
        UserTagVO userTagVO = new UserTagVO();
        userTagVO.setId(userDO.getId());
        userTagVO.setCreateTime(userDO.getCreateTime());
        userTagVO.setModifiedTime(data);
        userTagVO.setEmail(userDO.getEmail());
        userTagVO.setMobile(userDO.getMobile());
        userTagVO.setSiteSet(JSON.parseObject(userDO.getSites(), new TypeReference<Set<String>>() {}));
        userTagVO.setTagName(tagName);
        return userTagVO;
    }
}
