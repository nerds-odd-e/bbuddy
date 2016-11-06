package com.odde.bbuddy.common.mail;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String text;
}
