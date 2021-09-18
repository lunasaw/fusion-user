package com.luna.fusion.user.utils;

import com.luna.fusion.user.dto.UserTagDTO;
import com.luna.fusion.user.entity.UserTagDO;

/**
 * 外部DTO到内部DO的防腐层
 * 
 * @author Luna
 */
public class DTO2DOUtils {

    public static UserTagDO userTagDTO2UserTagDO(UserTagDTO userTagDTO) {
        UserTagDO userTagDO = new UserTagDO();
        userTagDO.setId(userTagDTO.getId());
        userTagDO.setCreateTime(userTagDTO.getCreateTime());
        userTagDO.setModifiedTime(userTagDTO.getModifiedTime());
        userTagDO.setVersion(userTagDTO.getVersion());
        userTagDO.setUserId(userTagDTO.getUserId());
        userTagDO.setTag1(userTagDTO.getUserTags().get(0));
        userTagDO.setTag2(userTagDTO.getUserTags().get(1));
        userTagDO.setTag3(userTagDTO.getUserTags().get(2));
        userTagDO.setTag4(userTagDTO.getUserTags().get(3));
        userTagDO.setTag5(userTagDTO.getUserTags().get(4));
        return userTagDO;
    }

}
