package com.iteknical.fusion.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iteknical.fusion.user.constant.URLConstant;

/**
 * @author czy@win10
 * @date 2020/2/2 21:40
 */
@Controller
@RequestMapping(URLConstant.USER)
public class UserController {

    @GetMapping(URLConstant.INDEX)
    public String showUserIndex() {
        return URLConstant.USER + URLConstant.S + URLConstant.INDEX;
    }

}
