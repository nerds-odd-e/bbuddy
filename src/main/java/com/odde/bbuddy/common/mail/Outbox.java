package com.odde.bbuddy.common.mail;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Service
public class Outbox {
    private final MailgunConfig config;

    @Autowired
    public Outbox(MailgunConfig config){
        this.config = config;
    }

    public boolean send(Mail mail){
        Response response = buildRequest().post(buildEntity(mail));
        return response.getStatus() == 200;
    }

    private Invocation.Builder buildRequest() {
        return JerseyClientBuilder.newClient()
            .register(new LoggingFeature(java.util.logging.Logger.getLogger("Mailgun"), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 8096))
            .register(config.authentication())
            .target(config.apiUrl())
            .path(config.domain())
            .path("messages")
            .request();
    }

    private Entity buildEntity(Mail mail) {
        Form form = new Form();
        form.param("from", mail.getFrom());
        form.param("to", mail.getTo());
        form.param("subject", mail.getSubject());
        form.param("text", mail.getText());
        return Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
    }
}
