package com.amr.project.converter;

import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, FavoriteMapper.class,
        ImageMapper.class, CountryMapper.class, CartItemMapper.class, ShopMapper.class,
        DiscountMapper.class, MessageMapper.class, ChatMapper.class, FeedbackMapper.class,
        ReviewMapper.class, UserInfoMapper.class, FavoriteMapper.class, AddressMapper.class})
public interface UserMapper extends MapperInterface<UserDto, User> {
}