package com.searchproductos.searchproductos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Ticket
{
    private Integer id;
    private ArrayList<PurchaseArticle> articles;
    private double total;
    private double totalCarrito;
    private Result statusCode;
}
