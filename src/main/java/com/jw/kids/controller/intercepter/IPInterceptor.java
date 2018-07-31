package com.jw.kids.controller.intercepter;
import com.jw.base.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * @author jw
 * @desc
 */
public class IPInterceptor implements HandlerInterceptor {
    private static final Logger LOG= Logger.getLogger(IPInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //过滤ip,若用户在白名单内，则放行
        String ipAddress= IPUtils.getRealIP(request);
        LOG.info("USER IP ADDRESS IS =>"+ipAddress);
        if(!StringUtils.isNotBlank(ipAddress))
            return false;
        if(!"192.168.13.1".equals(ipAddress)){
            response.getWriter().append("<h1 style=\"text-align:center;\">Not allowed!</h1>");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
