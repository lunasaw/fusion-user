package com.luna.fusion.user.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import com.luna.fusion.user.constant.UserTagNameConstant;
import com.luna.fusion.user.vo.TagVO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.iteknical.common.dto.constant.ResultCode;
import com.iteknical.common.utils.CommonUtils;
import com.iteknical.common.utils.HashUtils;
import com.iteknical.fusion.message.constant.MessageTypeConstant;
import com.iteknical.fusion.message.dto.MessageDTO;
import com.luna.fusion.user.constant.SitesConstant;
import com.luna.fusion.user.dao.UserDAO;
import com.luna.fusion.user.entity.UserDO;
import com.luna.fusion.user.exception.UserException;
import com.luna.fusion.user.exception.constant.BizResultCode;
import com.luna.fusion.user.support.MessageSupport;

/**
 * @author czy@win10
 * @date 2020/1/19 12:13
 */
@Service
public class UserService {

    @Autowired
    private UserDAO        userDAO;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private MessageSupport messageSupport;
    @Autowired
    private UserTagService userTagService;

    public void isAdmin(String sessionKey, String site) {
        if (!SitesConstant.isLegal(site)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        TagVO tagVO = new TagVO();
        tagVO.setName(UserTagNameConstant.IS_ADMIN);
        if (!userTagService.hasTag(sessionKey, site, tagVO.getName())) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
    }

    public UserDO get(String userMark) {
        if (CommonUtils.isEmailAddress(userMark)) {
            return userDAO.getByEmail(userMark);
        }
        return userDAO.getByMobile(userMark);
    }

    public String login(String userMark, String password, String site) {
        if (!SitesConstant.isLegal(site)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        String hash = hashPassword(password);

        UserDO userDO = null;
        if (CommonUtils.isEmailAddress(userMark)) {
            userDO = userDAO.getByEmailAndPassword(userMark, hash);
            // TODO 临时逻辑，兼容老hash算法，等待推动下线
            if (userDO == null) {
                hash = oldSHA512(oldSHA512(userMark) + oldSHA512(password));
                userDO = userDAO.getByEmailAndPassword(userMark, hash);
            }
        } else if (CommonUtils.isMobilePhoneNumber(userMark)) {
            userDO = userDAO.getByMobileAndPassword(userMark, hash);
        } else {
            throw new UserException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }

        // 登录失败
        if (userDO == null) {
            throw new UserException(BizResultCode.WRONG_USER_MARK_OR_PASSWORD,
                BizResultCode.MSG_WRONG_USER_MARK_OR_PASSWORD);
        }

        if (!hasSite(userDO, site)) {
            throw new UserException(BizResultCode.SITE_NOT_ACTIVATED, BizResultCode.MSG_SITE_NOT_ACTIVATED);
        }

        return sessionService.add(userDO.getId());
    }

    private boolean hasSite(UserDO userDO, String site) {
        // 直接有当前站点，直接成功
        if (SitesConstant.hasSite(userDO.getSites(), site)) {
            return true;
        }

        // 尝试激活站点
        activeSite(userDO, site);

        // 没有当前站点
        if (SitesConstant.hasSite(userDO.getSites(), site)) {
            return true;
        }

        return false;
    }

    private void activeSite(UserDO userDO, String site) {
        // 允许wednesday激活sweeney
        if (SitesConstant.hasSite(userDO.getSites(), SitesConstant.WEDNESDAY)
            && StringUtils.equals(site, SitesConstant.SWEENEY)) {
            Set<String> siteSet = new HashSet<>(JSON.parseArray(userDO.getSites(), String.class));
            siteSet.add(site);
            userDO.setSites(JSON.toJSONString(siteSet));
            userDAO.update(userDO);
        }
    }

    public void register(String userMark, String password, String site) {
        if (!SitesConstant.isLegal(site)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        String hash = hashPassword(password);

        UserDO userDO = new UserDO();
        userDO.setPassword(hash);
        userDO.setSites(JSON.toJSONString(ImmutableSet.of(site)));
        if (CommonUtils.isEmailAddress(userMark)) {
            userDO.setEmail(userMark);
        } else if (CommonUtils.isMobilePhoneNumber(userMark)) {
            userDO.setMobile(userMark);
        } else {
            throw new UserException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }

        userDAO.insert(userDO);
    }

    public UserDO getDOByUserId(String site, Long userId) {
        if (!SitesConstant.isLegal(site)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        return userDAO.get(userId);
    }

    public Long getUserIdBySessionKey(String sessionKey, String site) {
        UserDO userDO = getUserDOBySessionKey(sessionKey, site);
        if (userDO == null) {
            return null;
        }
        return userDO.getId();
    }

    public UserDO getUserDOBySessionKey(String sessionKey, String site) {
        if (!SitesConstant.isLegal(site)) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        Long userId = sessionService.get(sessionKey);
        if (userId == null) {
            return null;
        }

        UserDO userDO = userDAO.get(userId);

        // 没有当前站点
        if (SitesConstant.hasSite(userDO.getSites(), site) == false) {
            return null;
        }

        return userDO;
    }

    public boolean checkUserExist(String userMark) {
        UserDO userDO = null;
        if (CommonUtils.isEmailAddress(userMark)) {
            userDO = userDAO.getByEmail(userMark);
        } else if (CommonUtils.isMobilePhoneNumber(userMark)) {
            userDO = userDAO.getByMobile(userMark);
        } else {
            throw new UserException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }
        return userDO != null;
    }

    /**
     * hash密码
     * 
     * @param plain
     * @return
     */
    private static String hashPassword(String plain) {
        String temp = plain;
        for (int i = 0; i < plain.length(); i++) {
            temp = HashUtils.SHA512(temp);
        }
        return temp;
    }

    private static String oldSHA512(String plain) {
        if (StringUtils.isEmpty(plain)) {
            return null;
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            md.update(plain.getBytes("UTF-16LE"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        byte byteData[] = md.digest();

        String hashCodeBuffer = Base64.encodeBase64String(byteData);
        return hashCodeBuffer.toString();
    }

    /**
     * 修改密码
     * 
     * @param newPassword
     * @return
     */
    public void updatePassword(String sessionKey, String site, String newPassword) {
        UserDO userDO = getUserDOBySessionKey(sessionKey, site);
        if (userDO == null) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
        userDO.setPassword(hashPassword(newPassword));
        if (userDAO.updatePassword(userDO) != 1) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
    }

    /**
     * 修改邮箱
     * 
     * @param newEmail
     * @param sessionKey
     * @return
     */
    public void updateEmail(String sessionKey, String site, String newEmail) {
        if (CommonUtils.isEmailAddress(newEmail) == false) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "不是一个合法的邮箱地址");
        }

        UserDO userDO = getUserDOBySessionKey(sessionKey, site);
        if (userDO == null) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
        userDO.setEmail(newEmail);
        if (userDAO.update(userDO) != 1) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
    }

    /**
     * 修改手机
     * 
     * @param newMobile
     * @param sessionKey
     * @return
     */
    public void updateMobile(String sessionKey, String site, String newMobile) {
        if (CommonUtils.isMobilePhoneNumber(newMobile) == false) {
            throw new UserException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号");
        }

        UserDO userDO = getUserDOBySessionKey(sessionKey, site);
        if (userDO == null) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
        userDO.setMobile(newMobile);
        if (userDAO.update(userDO) != 1) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
    }

    /**
     * 重置密码
     *
     * @param userMark
     * @return
     */
    public void resetPassword(String userMark) {
        UserDO userDO = new UserDO();
        MessageDTO messageDTO = new MessageDTO();

        if (CommonUtils.isEmailAddress(userMark)) {
            userDO = userDAO.getByEmail(userMark);

            messageDTO.setTargetList(ImmutableList.of(userMark));
            messageDTO.setMessageType(MessageTypeConstant.EMAIL);
            messageDTO.setTemplateId(13);
        } else if (CommonUtils.isMobilePhoneNumber(userMark)) {
            userDO = userDAO.getByMobile(userMark);

            messageDTO.setTargetList(ImmutableList.of(userMark));
            messageDTO.setMessageType(MessageTypeConstant.MOBILE);
            messageDTO.setTemplateId(13);
        } else {
            throw new UserException(ResultCode.PARAMETER_INVALID, "不是一个合法的手机号或者邮箱地址");
        }

        String randomPassword = HashUtils.randomHex32();
        userDO.setPassword(hashPassword(randomPassword));
        if (userDAO.updatePassword(userDO) != 1) {
            throw new UserException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }

        messageDTO.setPlaceholderContent(ImmutableMap.of("newPassword", randomPassword));
        messageSupport.asyncSendMessage(messageDTO);
    }

}
