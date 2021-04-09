package com.searchproductos.searchproductos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto
{
    private ArrayList<PurchaseArticleDto> articles;
}
