package com.searchproductos.searchproductos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseArticleDto
{
    private Integer productId;
    private String name;
    private String brand;
    private Integer quantity;
}
