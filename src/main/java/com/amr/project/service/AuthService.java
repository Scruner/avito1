package com.amr.project.service;

import com.amr.project.model.dto.AuthDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
 public AuthDto tempCode(String phoneNumber) {
     // Метод принимает номер телефона, генерирует случайный пароль, возвращает его и
     // и отправляет на номер пользователя
     String passwordAuth= String.valueOf((long)(Math.random()*10000));
     System.out.println("На номер: "+phoneNumber + " отправлен код : "  + passwordAuth);
     LocalDateTime expired = LocalDateTime.now().plusSeconds(60);
     AuthDto authDto = new AuthDto(passwordAuth,expired);
     return authDto;
 }
}
