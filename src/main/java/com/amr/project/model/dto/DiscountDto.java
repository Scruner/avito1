package com.amr.project.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DiscountDto {
    private Long id;
    private int minOrder;
    private int percentage;
    private UserDto user;
    private Long shopId;
}
