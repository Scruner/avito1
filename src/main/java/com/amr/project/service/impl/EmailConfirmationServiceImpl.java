package com.amr.project.service.impl;

import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.EmailConfirmationService;
import com.amr.project.service.abstracts.Mailer;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {


    UserService userService;
    private Mailer mailer;

    public EmailConfirmationServiceImpl(UserService userService, Mailer mailer) {
        this.userService = userService;
        this.mailer = mailer;
    }

    @Override
    public void sendActivationCode(Long id) {
        //находим пользователя
        User user = userService.findById(id);

        //генерируем случайный 4х значный код активации
        Random random = new Random();
        String activationCode = random.ints(1,1111, 9999).toString();

        //Обновляем поле "activation_code" в таблице user для этого пользователя
        user.setActivationCode(activationCode);
        userService.update(user);

        //получаем почту, куда будем высылать код активации
        String email = user.getEmail();

        //Формируем и отправляем письмо с кодом активации пользователю
        String subject = "Код активации для подтверждения электронной почты";
        String text = "Используйте данный код для подтверждения вашего email";
        mailer.sendMail(email, subject, text);
    }

    @Override
    public boolean checkActivationCode(Long id, String activationCode) {
        //находим пользователя
        User user = userService.findById(id);

        // сравниваем activationCode, который ввёл пользователь с тем что хранится в БД, результат возвращаем на фронт
        // Обновляем по необходимости поле "activate" в таблице "user"
        if(user.getActivationCode().equals(activationCode)){
            user.setActivate(true);
            return true;
        }else {
            return false;
        }
    }
}
