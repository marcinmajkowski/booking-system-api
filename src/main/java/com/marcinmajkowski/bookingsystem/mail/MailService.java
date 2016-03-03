package com.marcinmajkowski.bookingsystem.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Log logger = LogFactory.getLog(MailService.class);

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(SimpleMailMessage mailMessage) {
        mailMessage.setFrom(mailUsername);

        mailSender.send(mailMessage);
    }
}
