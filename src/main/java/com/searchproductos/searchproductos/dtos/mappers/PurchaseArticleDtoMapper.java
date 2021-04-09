package com.searchproductos.searchproductos.dtos.mappers;

import com.searchproductos.searchproductos.dtos.PurchaseArticleDto;
import com.searchproductos.searchproductos.entities.PurchaseArticle;

import java.util.ArrayList;

public class PurchaseArticleDtoMapper
{

    public static PurchaseArticle mapToPurchaseArticle(PurchaseArticleDto p)
    {
        return new PurchaseArticle(p.getProductId(), p.getName(), p.getBrand(), p.getQuantity());
    }

    public static PurchaseArticleDto mapToDto(PurchaseArticle p)
    {
        return new PurchaseArticleDto(p.getProductId(), p.getName(), p.getBrand(), p.getQuantity());
    }

    public static ArrayList<PurchaseArticle> mapToPurchaseArticle(ArrayList<PurchaseArticleDto> articles)
    {
        ArrayList<PurchaseArticle> resp = new ArrayList<>();

        for (PurchaseArticleDto p: articles)
            resp.add(mapToPurchaseArticle(p));

        return resp;
    }

    public static ArrayList<PurchaseArticleDto> mapToDto(ArrayList<PurchaseArticle> articles)
    {
        ArrayList<PurchaseArticleDto> resp = new ArrayList<>();

        for (PurchaseArticle p: articles)
            resp.add(mapToDto(p));

        return resp;
    }
}
