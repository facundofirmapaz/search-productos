package com.searchproductos.searchproductos.dtos.mappers;

import com.searchproductos.searchproductos.dtos.ArticleResponseDto;
import com.searchproductos.searchproductos.entities.Article;

import java.util.ArrayList;

public class ArticleResponseDtoMapper
{
    public static ArticleResponseDto mapToDto(Article a)
    {
        return new ArticleResponseDto(a.getProductId(),
                                      a.getName(),
                                      a.getCategory(),
                                      a.getBrand(),
                                      a.getPrice(),
                                      a.getQuantity(),
                                      a.isFreeShipping(),
                                      a.getPrestige());
    }

    public static ArrayList<ArticleResponseDto> mapToDto(ArrayList<Article> articles)
    {
        ArrayList<ArticleResponseDto> resp = new ArrayList<>();

        for (Article a: articles)
            resp.add(mapToDto(a));

        return  resp;
    }
}
