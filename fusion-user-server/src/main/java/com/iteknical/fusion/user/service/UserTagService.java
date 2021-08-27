package com.iteknical.fusion.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.iteknical.fusion.user.constant.SitesConstant;
import com.iteknical.fusion.user.constant.UserTagNameConstant;
import com.iteknical.fusion.user.entity.UserDO;
import com.iteknical.fusion.user.req.TagReq;
import com.iteknical.fusion.user.utils.DO2VOUtils;
import com.iteknical.fusion.user.vo.TagVO;
import com.iteknical.fusion.user.vo.UserTagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteknical.common.dto.constant.ResultCode;
import com.iteknical.fusion.user.dao.TagDAO;
import com.iteknical.fusion.user.dao.UserTagDAO;
import com.iteknical.fusion.user.dto.UserTagDTO;
import com.iteknical.fusion.user.entity.TagDO;
import com.iteknical.fusion.user.entity.UserTagDO;
import com.iteknical.fusion.user.exception.UserException;
import com.iteknical.fusion.user.exception.constant.BizResultCode;
import com.iteknical.fusion.user.utils.DO2DTOUtils;
import com.iteknical.fusion.user.utils.DTO2DOUtils;

import static java.util.stream.Collectors.toMap;

/**
 * @author Iszychen@win10
 * @date 2020/2/10 20:54
 */
@Service
public class UserTagService {

    @Autowired
    private UserTagDAO     userTagDAO;
    @Autowired
    private TagDAO         tagDAO;
    @Autowired
    private UserService    userService;

    @Autowired
    private SessionService sessionService;

    public List<TagVO> listTag(String sessionKey, String site) {
        userService.isAdmin(sessionKey, site);
        return tagDAO.list().stream().map(tagDO -> {
            TagVO tagVO = new TagVO();
            tagVO.setName(tagDO.getName());
            return tagVO;
        }).collect(Collectors.toList());
    }

    public List<UserTagVO> listTagByUser(String sessionKey, String site, TagReq tagReq) {
        userService.isAdmin(sessionKey, site);
        List<TagDO> list = tagDAO.list();
        String userMask = tagReq.getUserMask();
        UserDO userDO = userService.get(userMask);
        UserTagDO userTagDO = userTagDAO.get(userDO.getId());
        UserTagDTO userTagDTO = DO2DTOUtils.UserTagDO2UserTagDTO(userTagDO);
        ArrayList<UserTagVO> tagVos = Lists.newArrayList();
        for (TagDO tagDO : list) {
            if ((userTagDTO.getUserTags().get(tagDO.getSequence() - 1) & tagDO.getMark()) == tagDO.getMark()) {
                tagVos.add(DO2VOUtils.userDO2UserTagVO(userDO, userTagDTO.getModifiedTime(), tagDO.getName()));
            }
        }
        return tagVos;
    }

    /**
     * 判断标识
     *
     * @param sessionKey
     * @param site
     * @param tagName
     * @return
     */
    public boolean hasTag(String sessionKey, String site, String tagName) {
        Long userIdBySessionKey = userService.getUserIdBySessionKey(sessionKey, site);
        if (userIdBySessionKey == null) {
            throw new UserException(BizResultCode.USER_NOT_SIGN_IN, BizResultCode.MSG_USER_NOT_SIGN_IN);
        }
        return hasTag(userIdBySessionKey, tagName);
    }

    /**
     * 判断标志
     *
     * @param tagName
     * @return
     */
    public boolean hasTag(Long userId, String tagName) {
        TagDO tagDO = tagDAO.get(tagName);
        if (tagDO == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        UserTagDO userTagDO = userTagDAO.get(userId);
        if (userTagDO == null) {
            return false;
        }
        UserTagDTO userTagDTO = DO2DTOUtils.UserTagDO2UserTagDTO(userTagDO);
        return (userTagDTO.getUserTags().get(tagDO.getSequence() - 1) & tagDO.getMark()) == tagDO.getMark();
    }

    public void addTag(String sessionKey, String site, String tagName, String usermask) {
        userService.isAdmin(sessionKey, site);
        UserDO userDO = userService.get(usermask);
        addTag(userDO.getId(), tagName);
    }

    public void addTag(Long userId, String tagName) {
        if (userId == null) {
            throw new UserException(BizResultCode.USER_NOT_SIGN_IN, BizResultCode.MSG_USER_NOT_SIGN_IN);
        }

        TagDO tagDO = tagDAO.get(tagName);
        if (tagDO == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        UserTagDO userTagDO = userTagDAO.get(userId);
        // insert if it's null
        if (userTagDO == null) {
            UserTagDO insertUserTagDO = new UserTagDO();
            insertUserTagDO.setUserId(userId);
            userTagDAO.insert(insertUserTagDO);
            userTagDO = userTagDAO.get(userId);
        }

        UserTagDTO userTagDTO = DO2DTOUtils.UserTagDO2UserTagDTO(userTagDO);
        List<Long> userTags = userTagDTO.getUserTags();
        userTags.set(tagDO.getSequence() - 1, (userTags.get(tagDO.getSequence() - 1)) | tagDO.getMark());
        userTagDAO.update(DTO2DOUtils.userTagDTO2UserTagDO(userTagDTO));
    }

    /**
     * 移除用户标志
     *
     * @param sessionKey
     * @param site
     * @param tagName
     */
    public void removeTag(String sessionKey, String site, String tagName) {
        Long userIdBySessionKey = userService.getUserIdBySessionKey(sessionKey, site);
        if (userIdBySessionKey == null) {
            throw new UserException(BizResultCode.USER_NOT_SIGN_IN, BizResultCode.MSG_USER_NOT_SIGN_IN);
        }

        TagDO tagDO = tagDAO.get(tagName);
        if (tagDO == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        UserTagDO userTagDO = userTagDAO.get(userIdBySessionKey);
        if (userTagDO == null) {
            return;
        }

        UserTagDTO userTagDTO = DO2DTOUtils.UserTagDO2UserTagDTO(userTagDO);
        List<Long> userTags = userTagDTO.getUserTags();
        userTags.set(tagDO.getSequence() - 1, (userTags.get(tagDO.getSequence() - 1)) ^ tagDO.getMark());
        userTagDAO.update(DTO2DOUtils.userTagDTO2UserTagDO(userTagDTO));
    }
}
