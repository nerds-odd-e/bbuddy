package com.odde.bbuddy.common.mail;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mailgun.properties")
public class MailgunConfig {
    private static final String API_URL = "https://api.mailgun.net/v3";
    private final String domain;
    private final String apiKey;
    private final String from;

    @Autowired
    public MailgunConfig(
            @Value("${mailgun.domain}") String domain,
            @Value("${mailgun.apiKey}") String apiKey,
            @Value("${mailgun.from}")String from) {
        this.domain = domain;
        this.apiKey = apiKey;
        this.from = from;
    }

    public HttpAuthenticationFeature authentication() {
        return HttpAuthenticationFeature
                .basicBuilder()
                .credentials("api", apiKey)
                .build();
    }

    public String apiUrl() {
        return API_URL;
    }

    public String domain() {
        return domain;
    }

    public String from(){
        return from;
    }
}
