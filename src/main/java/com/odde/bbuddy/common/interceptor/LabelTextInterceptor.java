package com.odde.bbuddy.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.stream.Stream;

import static com.odde.bbuddy.common.view.MessageSources.LABEL_TEXT_SHORT_NAME;

public class LabelTextInterceptor implements HandlerInterceptor {

    public static final String PREFIX = "label";
    public static final String VIEW_NAME_DELIMITER = "/";
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
        if (modelAndView == null || modelAndView.getViewName() == null){
            return;
        }
        allLabelMessageKeys(request).stream()
                .filter(key -> isLabelMessageKeyForView(key, modelAndView.getViewName()))
                .forEach(key -> addLabelMessage(key, modelAndView, request));
    }

    private ModelAndView addLabelMessage(String key, ModelAndView modelAndView, HttpServletRequest request) {
        return modelAndView.addObject(labelMessageCodeForView(key), exposedResourceBundleMessageSource.getMessageOverrided(key, null, request.getLocale()));
    }

    private String labelMessageCodeForView(String key) {
        return key.substring(key.indexOf(PREFIX));
    }

    private boolean isLabelMessageKeyForView(String key, String viewName) {
        return isAllNameInKey(viewName, key) || key.startsWith(PREFIX);
    }

    private boolean isAllNameInKey(String viewName, String key) {
        return Stream.of(viewName.split(VIEW_NAME_DELIMITER)).allMatch(key::contains);
    }

    private Set<String> allLabelMessageKeys(HttpServletRequest request) {
        return exposedResourceBundleMessageSource.getKeys(LABEL_TEXT_SHORT_NAME, request.getLocale());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //no implementation needed
    }

}
