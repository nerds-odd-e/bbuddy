package com.odde.bbuddy.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class LabelTextInterceptor implements HandlerInterceptor {

    private final ExposedResourceBundleMessageSource exposedResourceBundleMessageSource;

    public LabelTextInterceptor(ExposedResourceBundleMessageSource exposedResourceBundleMessageSource) {
        this.exposedResourceBundleMessageSource = exposedResourceBundleMessageSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String contextPath = request.getContextPath();
        Set<String> keys = exposedResourceBundleMessageSource.getKeys("resultMessages", request.getLocale());
        keys.stream()
                .filter(key -> key.contains(contextPath.replaceAll("/", ".")) && key.contains("label"))
                .forEach(key -> modelAndView.addObject(key, exposedResourceBundleMessageSource.getMessage(key, null, request.getLocale())));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
