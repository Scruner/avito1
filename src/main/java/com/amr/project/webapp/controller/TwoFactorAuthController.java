package com.amr.project.webapp.controller;

import com.amr.project.model.dto.UserDto;
import com.amr.project.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class TwoFactorAuthController {

    @PostMapping(path="/factorAuth")
    public ResponseEntity<UserDto> factorAuth(@RequestBody UserDto userDto) {
        if (userDto.isUsingTwoFactorAuth()) {
            String s=AuthService.generateSecretKey();
            userDto.setSecret(s);
            userDto.setQr_code(AuthService.getGoogleAuthenticatorBarCode(s,userDto.getEmail()));
            //необходимо вывести на фронте картинку QR кода и пользователь с помощью смартфона с установленным приложением
            // Google Authenticator сканирует QR код и получает 6 значный номер и сохраняет его
            // в поле activationCode.
        }
        return ResponseEntity.ok(userDto);
    }


    @PostMapping(path="/factorAuth/code")
    public ResponseEntity<UserDto> validateCode(@RequestBody UserDto userDto) {
        String expected_code= AuthService.getTOTPCode(userDto.getSecret());
        if (expected_code.equals((userDto.getActivationCode()))) {
            return ResponseEntity.ok(userDto);
        }  else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
