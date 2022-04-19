package com.amr.project.converter;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ItemMapper.class})
public interface CartItemMapper extends MapperInterface<CartItemDto, CartItem> {
    @Mapping(target = "userId", source = "user.id")
    @Override
    CartItemDto toDto(CartItem entity);

    @Mapping(target = "user.id", source = "userId")
    @Override
    CartItem toEntity(CartItemDto dto);
}