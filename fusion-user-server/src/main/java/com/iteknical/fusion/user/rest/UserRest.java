package com.iteknical.fusion.user.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iteknical.common.anno.MyValid;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.dto.constant.ResultCode;
import com.iteknical.fusion.user.constant.ConstantHolder;
import com.iteknical.fusion.user.constant.URLConstant;
import com.iteknical.fusion.user.entity.UserDO;
import com.iteknical.fusion.user.service.UserService;
import com.iteknical.fusion.user.utils.CookieUtils;
import com.iteknical.fusion.user.vo.*;

/**
 * @author Tony
 */
@RestController
@RequestMapping(URLConstant.API)
public class UserRest {
    @Autowired
    private UserService userService;

    @PostMapping(URLConstant.LOGIN)
    public ResultDTO<String> login(@RequestBody @MyValid LoginVO loginVO, HttpServletResponse response) {
        String sessionKey = userService.login(loginVO.getUserMark(), loginVO.getPassword(), loginVO.getSite());

        Cookie cookie = new Cookie(CookieUtils.SESSION_KEY_NAME, sessionKey);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * ConstantHolder.SESSION_EXPIRED_HOUR);
        response.addCookie(cookie);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, sessionKey);
    }

    /**
     * 用外标核验用户是否存在
     * <p>
     * 注册场景使用
     * </p>
     * 
     * @param userMark
     * @return
     */
    @GetMapping("checkUserExist")
    public ResultDTO<Boolean> checkUserExist(@RequestParam(name = "userMark") String userMark) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, userService.checkUserExist(userMark));
    }

    @PostMapping(URLConstant.REGISTER)
    public ResultDTO<Boolean> register(@RequestBody @MyValid RegisterVO registerVO) {
        userService.register(registerVO.getUserMark(), registerVO.getPassword(), registerVO.getSite());

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS, true);
    }

    /**
     * sessionKey换userId
     * <p>
     * 支持显式入参和从cookies中取
     * </p>
     * 
     * @param sessionKey
     * @return
     */
    @GetMapping(URLConstant.GET_USER_ID_BY_SESSION_KEY)
    public ResultDTO<Long> getUserIdBySessionKey(HttpServletRequest request,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestParam(name = "site") String site) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            userService.getUserIdBySessionKey(sessionKey, site));
    }

    /**
     * sessionKey换UserDO
     * 
     * @param request
     * @param sessionKey
     * @return
     */
    @GetMapping(URLConstant.GET_USER_DO_BY_SESSION_KEY)
    public ResultDTO<UserDO> getUserDOBySessionKey(HttpServletRequest request,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestParam(name = "site") String site) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            userService.getUserDOBySessionKey(sessionKey, site));
    }

    /**
     * 修改密码
     * 
     * @param request
     * @param site
     * @param updatePasswordVO
     * @return
     */
    @PostMapping(URLConstant.USER + URLConstant.S + "updatePassword")
    public ResultDTO<Void> updatePassword(HttpServletRequest request, @RequestParam(name = "site") String site,
        @RequestBody @MyValid UpdatePasswordVO updatePasswordVO) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        String sessionKey = CookieUtils.getSessionKeyFromRequest(request);

        userService.updatePassword(sessionKey, site, updatePasswordVO.getNewPassword());

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS);
    }

    /**
     * 修改邮箱
     * 
     * @param request
     * @param updateEmailVO
     * @param site
     * @return
     */
    @PostMapping(URLConstant.USER + URLConstant.S + "updateEmail")
    public ResultDTO<Void> updateEmail(HttpServletRequest request, @RequestParam(name = "site") String site,
        @RequestBody @MyValid UpdateEmailVO updateEmailVO) {
        // TODO 在老数据迁移完成之前，关闭修改邮箱入口
        return new ResultDTO<>(false, ResultCode.INTERFACE_OFFLINE, ResultCode.MSG_INTERFACE_OFFLINE);

        // if (StringUtils.isEmpty(site)) {
        // return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        // }
        // String sessionKey = CookieUtils.getSessionKeyFromRequest(request);
        // if (StringUtils.isBlank(sessionKey)) {
        // return new ResultDTO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        // }
        //
        // userService.updateEmail(sessionKey, site, updateEmailVO.getNewEmail());
        // return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS);
    }

    /**
     * 修改手机
     * 
     * @param request
     * @param updateMobileVO
     * @param site
     * @return
     */
    @PostMapping(URLConstant.USER + URLConstant.S + "updateMobile")
    public ResultDTO<Void> updateMobile(HttpServletRequest request, @RequestParam(name = "site") String site,
        @RequestBody @MyValid UpdateMobileVO updateMobileVO) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        String sessionKey = CookieUtils.getSessionKeyFromRequest(request);

        userService.updateMobile(sessionKey, site, updateMobileVO.getNewMobile());

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS);
    }

    @PostMapping(URLConstant.RESET_PASSWORD)
    public ResultDTO<Void> resetPassword(@RequestParam(name = "site") String site,
        @RequestBody @MyValid ResetPasswordVO resetPasswordVO) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        userService.resetPassword(resetPasswordVO.getUserMark());

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS);
    }
}
