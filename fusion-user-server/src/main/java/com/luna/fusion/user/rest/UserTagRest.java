package com.luna.fusion.user.rest;

import javax.servlet.http.HttpServletRequest;

import com.luna.common.anno.MyValid;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.fusion.user.req.TagReq;
import com.luna.fusion.user.vo.UserTagVO;
import com.luna.fusion.user.service.UserTagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.luna.fusion.user.constant.URLConstant;
import com.luna.fusion.user.utils.CookieUtils;
import com.luna.fusion.user.vo.TagVO;

import java.util.List;

/**
 * @author Iszychen@win10
 * @date 2020/2/16 18:49
 */
@RestController
@RequestMapping(URLConstant.API)
@CrossOrigin
public class UserTagRest {

    @Autowired
    private UserTagService userTagService;

    /**
     * 查看所有标
     *
     * @param request
     * @param site
     * @return
     */
    @GetMapping(URLConstant.TAG_LIST)
    public ResultDTO<List<TagVO>> listTag(HttpServletRequest request,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestParam(name = "site") String site) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            userTagService.listTag(sessionKey, site));
    }

    /**
     * 查看用户所有标
     *
     * @param request
     * @param site
     * @return
     */
    @PostMapping(URLConstant.TAG_LIST)
    public ResultDTO<List<UserTagVO>> listTagByUser(HttpServletRequest request,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestParam(name = "site") String site,
        @RequestBody @MyValid TagReq tagReq) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            userTagService.listTagByUser(sessionKey, site, tagReq));
    }

    /**
     * 判断是否有标
     *
     * @param request
     * @param site
     * @param tagVO
     * @return
     */
    @PostMapping(URLConstant.HAS_TAG)
    public ResultDTO<Boolean> hasTag(HttpServletRequest request,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestParam(name = "site") String site,
        @RequestBody @MyValid TagVO tagVO) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            userTagService.hasTag(sessionKey, site, tagVO.getName()));
    }

    /**
     * 加标
     *
     * @param request
     * @param site
     * @param tagVO
     * @return
     */
    @PostMapping(URLConstant.ADD_TAG)
    public ResultDTO<Void> addTag(HttpServletRequest request, @RequestParam(name = "site") String site,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestBody @MyValid TagVO tagVO) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        userTagService.addTag(sessionKey, site, tagVO.getName(), tagVO.getUserMask());
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS);
    }

    /**
     * 去标
     *
     * @param request
     * @param site
     * @param tagVO
     * @return
     */
    @PostMapping(URLConstant.REMOVE_TAG)
    public ResultDTO<Void> removeTag(HttpServletRequest request, @RequestParam(name = "site") String site,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestBody @MyValid TagVO tagVO) {
        if (StringUtils.isEmpty(site)) {
            return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        userTagService.removeTag(sessionKey, site, tagVO.getName());
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS);
    }

}
