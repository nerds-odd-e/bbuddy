package com.odde.bbuddy.common.mail;

import com.odde.bbuddy.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class OutboxIntegrationTest {
    @Autowired
    private Outbox outbox;

    @Test
    public void send() {
        Mail mail = new Mail();
        mail.setSubject("Test subject");
        mail.setText("Text body");
        mail.setFrom("NONEXIST@example.com");
        mail.setTo("bbuddy@mea.tyjie.com");
        assertThat(outbox.send(mail)).isTrue();
    }

}
