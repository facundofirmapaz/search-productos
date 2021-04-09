package com.searchproductos.searchproductos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class TicketDto
{
    private Integer id;
    private ArrayList<PurchaseArticleDto> articles;
    private double total;
    private double totalCarrito;
    private ResultDto statusCode;
}
