package com.jw.shiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jw
 * @desc
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/loginJump",method = RequestMethod.GET)
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/src/login/login.html");
    }

    @RequestMapping(value = "/loginSuccess",method = RequestMethod.GET)
    public void loginSuccess(HttpServletResponse response) throws IOException {
        response.sendRedirect("/src/kids/kidsMain.html");
    }


}
