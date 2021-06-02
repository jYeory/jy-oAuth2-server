package com.jyeory.sso.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CmFunction {

	/**
     * @return 접속한 Client IP 반환
     */
    public static String getClientIpAddr() {
        HttpServletRequest  req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String              ip  = new String();

        ip = req.getHeader("X-Forwarded-For");

        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }

        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }

        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("HTTP_CLIENT_IP");
        }

        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("HTTP_X_FORWARDED_FOR");
        }
        
        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("X-Real-IP");
        }
        
        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("X-RealIP");
        }
        
        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }

        return ip;
    }
}
