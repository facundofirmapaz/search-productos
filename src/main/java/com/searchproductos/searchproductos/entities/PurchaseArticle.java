package com.searchproductos.searchproductos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseArticle
{
    private Integer productId;
    private String name;
    private String brand;
    private Integer quantity;
}
