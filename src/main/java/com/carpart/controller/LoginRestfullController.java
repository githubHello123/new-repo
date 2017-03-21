package com.carpart.controller;

import com.carpart.entity.User;
import com.gx.util.ResourceUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
@RequestMapping(value = "/")
public class LoginRestfullController {

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "login")
    public String login(ModelMap modelMap, HttpServletRequest request) {
        User user = ResourceUtil.getSessionUserName();
        if (user != null) {
            modelMap.put("userName", user.getUserName());
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
                    continue;
                }
            }
            return "main/shortcut_main";
        } else {
            return "login/login";
        }

    }
}
