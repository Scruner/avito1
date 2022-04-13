package com.amr.project.service.abstracts;


public interface EmailService {

    void sendEmail(String toAddress, String subject, String text);
}
