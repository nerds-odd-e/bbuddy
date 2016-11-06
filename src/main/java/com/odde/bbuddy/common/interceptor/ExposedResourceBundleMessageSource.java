package com.odde.bbuddy.common.interceptor;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.Set;

public class ExposedResourceBundleMessageSource extends ResourceBundleMessageSource {

    public Set<String> getKeys(String basename, Locale locale) {
        setBasename(basename);
        return getResourceBundle(basename, locale).keySet();
    }

    public String getMessageOverrided(String code, Object[] args, Locale locale) {
        return getMessage(code, args, locale);
    }

}
