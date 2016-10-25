package com.odde.bbuddy.acceptancetest.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "cucumber-glue", proxyMode = TARGET_CLASS)
public class ApplicationConfigurations {

    ConfigurableApplicationContext applicationContext;

    @Autowired
    public ApplicationConfigurations(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private final Map<String, Object> overwrittenConfigurationNames = new HashMap<>();

    public void overwrite(String propertyName, Object propertyValue) {
        overwrittenConfigurationNames.put(propertyName, propertyValue);
        systemProperties().put(propertyName, propertyValue);
    }

    public Map<String, Object> systemProperties() {
        return applicationContext.getEnvironment().getSystemProperties();
    }

    public void restore() {
        overwrittenConfigurationNames.keySet().forEach(name -> systemProperties().remove(name));
    }

    public Object getOverWritten(String name) {
        return overwrittenConfigurationNames.get(name);
    }
}
