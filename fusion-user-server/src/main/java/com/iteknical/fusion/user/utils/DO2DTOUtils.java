package com.iteknical.fusion.user.utils;

import com.google.common.collect.Lists;
import com.iteknical.fusion.user.dto.UserTagDTO;
import com.iteknical.fusion.user.entity.UserTagDO;

/**
 * @author Tony
 */
public class DO2DTOUtils {

    public static UserTagDTO UserTagDO2UserTagDTO(UserTagDO userTagDO) {
        UserTagDTO userTagDTO = new UserTagDTO();
        userTagDTO.setId(userTagDO.getId());
        userTagDTO.setCreateTime(userTagDO.getCreateTime());
        userTagDTO.setModifiedTime(userTagDO.getModifiedTime());
        userTagDTO.setVersion(userTagDO.getVersion());
        userTagDTO.setUserId(userTagDO.getUserId());
        userTagDTO.setUserTags(Lists.newArrayList(
            userTagDO.getTag1(),
            userTagDO.getTag2(),
            userTagDO.getTag3(),
            userTagDO.getTag4(),
            userTagDO.getTag5()));
        return userTagDTO;
    }

}
