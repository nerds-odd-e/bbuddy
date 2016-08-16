package cucumber.runtime.java.spring;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScopeEnabler {

    @Bean
    public CustomScopeConfigurer glueScopeConfigurer(){
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();

        configurer.addScope("cucumber-glue", new GlueCodeScope());

        return configurer;
    }
}
