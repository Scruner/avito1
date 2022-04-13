package com.amr.project.service.abstracts;

public interface MailService {
    void sendEmail(String toAddress, String subject, String text);
}
