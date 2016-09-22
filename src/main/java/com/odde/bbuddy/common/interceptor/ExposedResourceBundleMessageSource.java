package com.odde.bbuddy.common.interceptor;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class ExposedResourceBundleMessageSource extends ResourceBundleMessageSource {

    public Set<String> getKeys(String basename, Locale locale) {
        setBasename(basename);
        ResourceBundle bundle = getResourceBundle(basename, locale);
        return bundle.keySet();
    }

}
