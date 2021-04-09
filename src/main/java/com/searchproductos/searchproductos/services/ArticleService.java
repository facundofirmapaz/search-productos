package com.searchproductos.searchproductos.services;

import com.searchproductos.searchproductos.entities.Article;
import com.searchproductos.searchproductos.entities.PurchaseArticle;
import com.searchproductos.searchproductos.entities.Ticket;

import java.util.ArrayList;
import java.util.Map;

public interface ArticleService
{
    ArrayList<Article> getArticles(Map<String, String> params);

    Ticket newTicket(ArrayList<PurchaseArticle> articulos, Integer carrito);
}
