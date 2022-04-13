package com.amr.project.webapp.controller;

import com.amr.project.service.abstracts.EmailConfirmationService;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/email_confirmation")
public class EmailConfirmRestController {

    private final EmailConfirmationService emailConfirmService;

    //посылаем activationCode на почту пользователя
    @PostMapping("/send")
    void sendCodeForEmailConfirmation(@RequestBody Long id) {
        emailConfirmService.sendActivationCode(id);
    }


    //проверяем activationCode введённый пользователем в форму
    @PostMapping("/confirm")
    boolean confirmEmail(@RequestBody JsonObject jsonObject) {
        Long id = jsonObject.get("id").getAsLong();
        String activationCode = jsonObject.get("activationCode").getAsString();
        return emailConfirmService.checkActivationCode(id, activationCode);
    }

}


