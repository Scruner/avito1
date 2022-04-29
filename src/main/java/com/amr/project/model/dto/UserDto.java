package com.amr.project.model.dto;

import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
import com.amr.project.model.enums.Role;
import lombok.Data;
import lombok.ToString;

import java.util.Calendar;
import java.util.List;

@Data
@ToString
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean activate;
    private String activationCode;
    private String phone;
    private String firstName;
    private String lastName;
    private int age;
    private List<AddressDto> address;
    private Role role;
    private Gender gender;
    private Calendar birthday;
    private ImageDto image;
    private List<CouponDto> coupons;
    private List<CartItemDto> cart;
    private List<OrderDto> orders;
    private List<ShopDto> shops;
    private List<FavoriteDto> favorites;
    private List<DiscountDto> discounts;
    private boolean isUsingTwoFactorAuth;
    private String secret;
    private String qr_code;
}
