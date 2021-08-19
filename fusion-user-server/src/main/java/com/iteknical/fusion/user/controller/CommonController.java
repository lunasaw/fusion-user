package com.iteknical.fusion.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iteknical.fusion.user.constant.URLConstant;

/**
 * @author Tony
 * @date 2019年12月11日 上午11:42:29
 */
@Controller
@RequestMapping(URLConstant.S)
public class CommonController {
    private final static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @GetMapping(URLConstant.LOGIN)
    public String showLogin() {
        return URLConstant.LOGIN;
    }

    @GetMapping(URLConstant.REGISTER)
    public String showRegister() {
        return URLConstant.REGISTER;
    }

    @GetMapping(URLConstant.RESET_PASSWORD)
    public String showResetPassword() {
        return URLConstant.RESET_PASSWORD;
    }

}
