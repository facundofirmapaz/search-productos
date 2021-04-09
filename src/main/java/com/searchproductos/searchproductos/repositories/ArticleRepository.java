package com.searchproductos.searchproductos.repositories;

import com.searchproductos.searchproductos.entities.Article;
import com.searchproductos.searchproductos.entities.PurchaseArticle;

import java.util.ArrayList;
import java.util.Map;

public interface ArticleRepository
{
    ArrayList<Article> getAll();

    ArrayList<Article> getByFilters(Map<String, String> filtrosValidos);

    Article getById(Integer productId);

    Article getByPurchaseArticle(PurchaseArticle p);
}
