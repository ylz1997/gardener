package com.jw.shiro.controller;

import com.jw.shiro.bean.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login(UserInfo userInfo) throws Exception{
/*
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        logger.info("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        response.sendRedirect("/src/login/login.html");*/

        Map resultMap = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassword());
        try {
            subject.login(token);
            resultMap.put("token", subject.getSession().getId());
            resultMap.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            resultMap.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            resultMap.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            resultMap.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap.toString();



    }

/*    @RequestMapping(value = "/loginJump",method = RequestMethod.GET)
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/src/login/login.html");
    }*/
/*
    @RequestMapping(value = "/loginSuccess",method = RequestMethod.GET)
    public void loginSuccess(HttpServletResponse response) throws IOException {
        response.sendRedirect("/src/kids/kidsMain.html");
    }*/


}
