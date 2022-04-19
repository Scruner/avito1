package com.amr.project.service.impl;

import com.amr.project.service.abstracts.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class MailerImpl implements Mailer {
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendMail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
