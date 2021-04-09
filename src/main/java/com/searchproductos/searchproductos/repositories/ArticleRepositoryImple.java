package com.searchproductos.searchproductos.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchproductos.searchproductos.entities.Article;
import com.searchproductos.searchproductos.entities.PurchaseArticle;
import com.searchproductos.searchproductos.exceptionsHandlers.ArticleNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ArticleRepositoryImple implements ArticleRepository
{
    @Override
    public ArrayList<Article> getAll()
    {
        ArrayList<Article> resp = new ArrayList<>();

        var data = loadDataBase();

        if (data != null)
            resp = data;

        return resp;
    }

    @Override
    public ArrayList<Article> getByFilters(Map<String, String> filtrosValidos)
    {
        ArrayList<Article> resp = new ArrayList<>();

        var data = loadDataBase();

        if (data != null)
        {
            //recorro el map de filtros y por cada uno lo aplico a la lista resp
            resp = data;
            for(Map.Entry<String, String> filtro : filtrosValidos.entrySet())
                resp = (ArrayList<Article>) resp.stream()
                                                .filter(a -> a.evaluarFiltro(filtro.getKey(), filtro.getValue()))
                                                .collect(Collectors.toList());
        }

        return resp;
    }

    @SneakyThrows
    @Override
    public Article getById(Integer productId)
    {
        var data = loadDataBase();

        Article article = null;

        if (data != null)
        {
            for (Article a : data)
            {
                if (a.getProductId().equals(productId))
                {
                    article = a;
                    break;
                }
            }
        }

        if(article == null)
            throw new ArticleNotFoundException("Articulo no encontrado: " + productId);

        return article;
    }

    @SneakyThrows
    @Override
    public Article getByPurchaseArticle(PurchaseArticle p)
    {
        var data = loadDataBase();

        Article article = null;

        if (data != null)
        {
            for (Article a : data)
            {
                if (a.getProductId().equals(p.getProductId()) &&
                    a.getBrand().equals(p.getBrand()) &&
                    a.getName().equals(p.getName()))
                {
                    article = a;
                    break;
                }
            }
        }

        if(article == null)
            throw new ArticleNotFoundException("Articulo no encontrado o con campos incorrectos: " + p.toString());

        return article;
    }

    private ArrayList<Article> loadDataBase()
    {
        File file;
        ArrayList<Article> articles = null;

        try
        {
            file = ResourceUtils.getFile("classpath:dbProductos.json");
            ObjectMapper objectMapper = new ObjectMapper();
            com.fasterxml.jackson.core.type.TypeReference<ArrayList<Article>> typeReference = new com.fasterxml.jackson.core.type.TypeReference<>() {};

            articles = objectMapper.readValue(file, typeReference);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return articles;
    }
}
