package com.iteknical.fusion.user.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iteknical.common.anno.MyValid;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.dto.constant.ResultCode;
import com.iteknical.fusion.user.constant.URLConstant;
import com.iteknical.fusion.user.service.UserTagService;
import com.iteknical.fusion.user.utils.CookieUtils;
import com.iteknical.fusion.user.vo.TagVO;

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

        userTagService.addTag(sessionKey, site, tagVO.getName(), tagVO.getUserMask(), tagVO.getSite());
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
