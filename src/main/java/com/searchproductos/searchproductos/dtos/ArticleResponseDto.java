package com.searchproductos.searchproductos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleResponseDto
{
    private Integer productId;
    private String name;
    private String category;
    private String brand;
    private double price;
    private Integer quantity;
    private boolean freeShipping;
    private String prestige;
}
