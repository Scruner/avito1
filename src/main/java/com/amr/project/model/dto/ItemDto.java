package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal basePrice;
    private BigDecimal price;
    private Long categoryId;
    private List<ImageDto> images;
    private List<ReviewDto> reviews;
    private int count;
    private double rating;
    private String description;
    private int discount;
    private long shopId;
    private boolean isModerated;
    private String moderatedRejectReason;
    private boolean isPretendedToBeDeleted;
}