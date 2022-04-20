package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class AuthDto {
    private String code;
    private LocalDateTime expired;

}
