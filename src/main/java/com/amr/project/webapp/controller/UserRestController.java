package com.amr.project.webapp.controller;

import com.amr.project.converter.ImageMapper;
import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.service.abstracts.MailService;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private UserService userService;
    private MailService mailService;
    private UserMapper userMapper;
    private ImageMapper imageMapper;

    @PatchMapping(value = "/change-pass")
    public ResponseEntity<UserDto> changePassword(@RequestBody UserDto userDto) {
        if(userDto.getPassword() != null || !userDto.getPassword().equals("")) {
            userService.update(userMapper.toEntity(userDto));
            mailService.sendEmail(userDto.getEmail(),
                    "password notification",
                    "your password has been changed");
            return ResponseEntity.ok(userDto);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping(value = "/change-image/{id}")
    public ResponseEntity<Void> changeImage(@RequestParam(value = "id") Long id, @RequestBody ImageDto imageDto) {
        userService.findById(id).setImage(imageMapper.toEntity(imageDto));
        return ResponseEntity.ok().build();
    }
}
