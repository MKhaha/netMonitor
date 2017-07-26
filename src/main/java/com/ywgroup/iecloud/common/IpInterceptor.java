package com.ywgroup.iecloud.common;

import com.ywgroup.iecloud.util.UserIp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guotao on 2017/7/21.
 * com.mmall.common
 * qrprinter
 */
public class IpInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(IpInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String ipAddress = UserIp.getUsrIPAddr(httpServletRequest);
        logger.warn("user ipAddress is : " + ipAddress);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
