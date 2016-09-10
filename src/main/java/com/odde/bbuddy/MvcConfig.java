package com.odde.bbuddy;

import com.odde.bbuddy.session.controller.AuthenticationInterceptor;
import com.odde.bbuddy.session.controller.ErrorMessageInterceptor;
import com.odde.bbuddy.session.controller.LayoutNavigationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor());
        registry.addInterceptor(new LayoutNavigationInterceptor());
        registry.addInterceptor(new ErrorMessageInterceptor());
        super.addInterceptors(registry);
    }
}