package com.searchproductos.searchproductos.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchproductos.searchproductos.dtos.ArticleResponseDto;
import com.searchproductos.searchproductos.dtos.mappers.ArticleResponseDtoMapper;
import com.searchproductos.searchproductos.entities.Article;
import com.searchproductos.searchproductos.services.ArticleService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    ArticleService articleService = Mockito.mock(ArticleService.class);

    ArticleController articleController = new ArticleController(articleService);

    private final String ORDERED_BY_PRESTIGE = "src/test/resources/productFilterByCategory.json";

    @Test
    void getWithValidProductId()
    {
        ArrayList<Article> articles = new ArrayList<>();
        Article article = new Article(1,
                                      "Zapatillas",
                                      "Indumentaria",
                                      "Nike",
                                      450,
                                      5,
                                      true,
                                      "***");
        articles.add(article);
        Map<String, String> filtros = new HashMap<>();
        filtros.put("productId", "1");

        Mockito.when(articleService.getArticles(filtros)).thenReturn(articles);


        ResponseEntity<?> respuestaServicio = articleController.get(filtros);
        assertEquals(ArticleResponseDtoMapper.mapToDto(articles), respuestaServicio.getBody());
    }

    @SneakyThrows
    @Test
    void getWithValidCategoryFilter()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Article> articlesExpected = objectMapper.readValue(new File(ORDERED_BY_PRESTIGE), new TypeReference<>(){});

        Map<String, String> filtros = new HashMap<>();
        filtros.put("category", "Herramientas");

        Mockito.when(articleService.getArticles(filtros)).thenReturn(articlesExpected);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/articles?category=Herramientas").accept(MediaType.ALL))
                                          .andReturn();

        ArrayList<ArticleResponseDto> actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals(ArticleResponseDtoMapper.mapToDto(articlesExpected), actual);
    }

    @Test
    void purchaseValidPurchaseArticleDto()
    {

    }
}