package com.amr.project.webapp.controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.AuthDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.service.AuthService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@RequestMapping("/user")
public class TwoFactorAuthController {
    private UserService userService;
    private UserMapper userMapper;
    private AuthService authService;
    private AuthDto temp_authDto;


    @PostMapping(path="/factorAuth")
    public ResponseEntity<UserDto> factorAuth(@RequestBody UserDto userDto) {
        if (userDto.isUsingTwoFactorAuth()) {
           temp_authDto = authService.tempCode(userDto.getPhone());
        }
        return ResponseEntity.ok(userDto);
    }
    @PostMapping(path="/factorAuth/code")
    public ResponseEntity<UserDto> validateCode(@RequestBody UserDto userDto, AuthDto authDto) {
        if (temp_authDto.getCode().equals(authDto.getCode()) && (temp_authDto.getExpired().isBefore(LocalDateTime.now()))) {
            return ResponseEntity.ok(userDto);
        }  else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
