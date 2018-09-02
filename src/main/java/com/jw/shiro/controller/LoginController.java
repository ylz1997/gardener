package com.jw.shiro.controller;

import com.jw.base.JsonUtil;
import com.jw.shiro.bean.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jw
 * @desc
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String login(UserInfo userInfo) throws Exception{
        Map resultMap = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassword());
        try {
            subject.login(token);
            resultMap.put("token", subject.getSession().getId());
            resultMap.put("msg", "登录成功,正在跳转主页...");
            resultMap.put("url", "/src/kids/kidsMain.html");
            resultMap.put("loginNm", ((UserInfo)subject.getPrincipal()).getName());
        } catch (IncorrectCredentialsException e) {
            resultMap.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            resultMap.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            resultMap.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtil.convertObject2Json(resultMap);

    }

    @RequestMapping(value = "/checkPermission" , method = RequestMethod.GET)
    public String checkPermission(String permission){
        Map resultMap = new HashMap();
        try{
            Subject subject = SecurityUtils.getSubject();
            subject.checkPermission(permission);
            resultMap.put("result", true);
        }catch (Exception e){
            resultMap.put("result", false);
        }
        return JsonUtil.convertObject2Json(resultMap);
    }


}
