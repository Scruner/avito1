package com.amr.project.converter;

import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ShopMapper.class, UserMapper.class})
public interface DiscountMapper extends MapperInterface<DiscountDto, Discount> {
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "userId", source = "user.id")
    @Override
    DiscountDto toDto(Discount entity);
}