package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModeratorListDto {
    private List<ItemDto> item;
    private List<ShopDto> shop;
    private List<ReviewDto> review;
}
