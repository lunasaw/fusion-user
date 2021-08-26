package com.iteknical.fusion.user.service;

import java.util.List;

import com.iteknical.fusion.user.constant.UserTagNameConstant;
import com.iteknical.fusion.user.entity.UserDO;
import com.iteknical.fusion.user.vo.TagVO;
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

    public List<TagDO> listTag(String sessionKey, String site) {
        userService.isAdmin(sessionKey, site);
        return tagDAO.list();
    }

    /**
     * 判断标志
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

        TagDO tagDO = tagDAO.get(tagName);
        if (tagDO == null) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        UserTagDO userTagDO = userTagDAO.get(userIdBySessionKey);
        if (userTagDO == null) {
            return false;
        }
        UserTagDTO userTagDTO = DO2DTOUtils.UserTagDO2UserTagDTO(userTagDO);
        return (userTagDTO.getUserTags().get(tagDO.getSequence() - 1) & tagDO.getMark()) == tagDO.getMark();
    }

    public void addTag(String sessionKey, String site, String tagName, String usermask, String targetSite) {
        userService.isAdmin(sessionKey, site);
        UserDO userDO = userService.get(usermask);
        addTag(userDO.getId(), targetSite, tagName);
    }

    public void addTag(Long userId, String site, String tagName) {
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
     * 给用户增加标志
     *
     * @param sessionKey
     * @param site
     * @param tagName
     */
    public void addTag(String sessionKey, String site, String tagName) {
        Long userIdBySessionKey = userService.getUserIdBySessionKey(sessionKey, site);
        addTag(userIdBySessionKey, site, tagName);
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
