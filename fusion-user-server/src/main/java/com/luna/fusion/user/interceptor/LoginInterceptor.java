package com.luna.fusion.user.interceptor;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.dto.constant.ResultCode;
import com.luna.fusion.user.exception.UserException;
import com.luna.fusion.user.exception.constant.BizResultCode;
import com.luna.fusion.user.service.UserService;
import com.luna.fusion.user.utils.CookieUtils;

/**
 * @author Iszychen@win10
 * @date 2020/2/4 21:49
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private Logger              logger   = LoggerFactory.getLogger(LoginInterceptor.class);

    private static final String ENCODING = "utf-8";

    @Value("${fusion.user.frontend.host}")
    private String              fusionUserFrontEndHost;

    @Autowired
    private UserService         userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (StringUtils.isBlank(request.getParameter("site"))) {
            throw new UserException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        String site = request.getParameter("site");

        String originURL = fusionUserFrontEndHost + request.getRequestURI().toString();
        if (request.getQueryString() != null) {
            originURL = originURL + "?" + request.getQueryString();
        }

        // 重定向url
        String redirectURL = fusionUserFrontEndHost + "/fusion-user/login?site=" + site + "&redirectURL="
            + URLEncoder.encode(originURL, ENCODING);

        String sessionKey = CookieUtils.getSessionKeyFromRequest(request);
        if (checkSessionKey(sessionKey, site) == false) {
            // 这儿通过调用链接中有无/api/来判断是否是rest调用，不甚合理，但暂无它法
            if (request.getRequestURL().indexOf("/api/") == -1) {
                logger.info("sessionKey not found, redirectURL={}", redirectURL);
                response.sendRedirect(redirectURL);
            } else {
                response.setHeader("Content-Type", "application/json");
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSON.toJSONString(
                    new ResultDTO(false, BizResultCode.USER_NOT_SIGN_IN, BizResultCode.MSG_USER_NOT_SIGN_IN)));

                printWriter.flush();
                printWriter.close();
            }
            return false;
        }

        return true;
    }

    private boolean checkSessionKey(String sessionKey, String site) {
        if (StringUtils.isBlank(sessionKey)) {
            return false;
        }
        Long userId = userService.getUserIdBySessionKey(sessionKey, site);
        return userId != null;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        // logger.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // logger.info("afterCompletion...");
    }
}
