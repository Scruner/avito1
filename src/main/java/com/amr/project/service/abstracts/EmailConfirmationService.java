package com.amr.project.service.abstracts;

public interface EmailConfirmationService {
    void sendActivationCode(Long id);
    boolean checkActivationCode(Long id, String activationCode);
}
