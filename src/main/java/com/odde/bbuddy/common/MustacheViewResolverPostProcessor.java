package com.odde.bbuddy.common;

import com.github.mjeanroy.springmvc.view.mustache.MustacheViewResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MustacheViewResolverPostProcessor {

    @Bean
    public BeanPostProcessor enableExposeRequestAttributeWhileMustacheAutoConfigurationIsExcluded() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (beanName.equals("mustacheViewResolver"))
                    ((MustacheViewResolver)bean).setExposeRequestAttributes(true);
                return bean;
            }
        };
    }
}
