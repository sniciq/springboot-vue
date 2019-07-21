package com.demo.SpringBoot.web.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.SpringBoot.web.AjaxOut;
import com.demo.SpringBoot.web.SessionUtil;

public class AppInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static List<String> uncheckUrls = Lists.newArrayList(
        "/login","/sys/login"
    );

    private static List<String> uncheckUrlEnds = Lists.newArrayList(
        ".js",
        ".css",
        ".gif",
        ".png",
        ".woff",
        ".ttf"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        String url = request.getRequestURI();// 得到请求URL
        url = url.replace(request.getContextPath(), "");

        if(url.startsWith("/resources/")) {
           return true;
        }

        for(String s : uncheckUrlEnds) {
            if(url.endsWith(s)) {
                return true;
            }
        }

        if(isUserLogined()) {//已经登录
            return true;
        }
        else {//没有登录
            if(uncheckUrls.contains(url)) {//允许访问登录页面资源
                return true;
            }
            else {
                sendRedirectTo(request, response, "timeout", "/login");
                return false;
            }
        }
    }

    public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession();
    }

    private boolean isUserLogined() {
        //return SessionUtil.getLoginUser() != null && SessionUtil.getLoginUser().getStatus() == 1;
        //FIXME 需要将此处改为用户登录的限制即可
        return SessionUtil.getLoginUser() != null;
    }

    private void sendRedirectTo(HttpServletRequest request, HttpServletResponse response, String type, String url) {
        try {
            SessionUtil.logout();
            String httpTag = request.getHeader("Request-By");
            if(httpTag != null && httpTag.equals("AgHttp")) {
                Map<String, Object> obj = new HashMap<String, Object>();
                obj.put("success", true);
                obj.put("result", type);
                obj.put("info","登录超时或者权限被收回！请重新登录！");
                obj.put("redirectURL", request.getContextPath() + url);
                AjaxOut.responseJson(response, obj);
            }
            else {
                response.sendRedirect(request.getContextPath() + url);
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }
}
