package com.amr.project.service.impl;

import com.amr.project.service.abstracts.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String username;

    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmail(String toAddress, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(toAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        emailSender.send(mailMessage);
    }
}
