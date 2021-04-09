package com.searchproductos.searchproductos.controllers;

import com.searchproductos.searchproductos.dtos.ArticleResponseDto;
import com.searchproductos.searchproductos.dtos.PurchaseDto;
import com.searchproductos.searchproductos.dtos.mappers.ArticleResponseDtoMapper;
import com.searchproductos.searchproductos.dtos.mappers.PurchaseArticleDtoMapper;
import com.searchproductos.searchproductos.dtos.mappers.TicketDtoMapper;
import com.searchproductos.searchproductos.entities.Ticket;
import com.searchproductos.searchproductos.services.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ArticleController extends BaseController
{
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService)
    {
        this.articleService = articleService;
    }

    //metodo de get: si vienen parametros los usa de filtro, tambien con parametro de ordenamiento
    //maximo 2 parametros de filtro (no cuenta el de ordenamiento)
    @GetMapping("/articles")
    public ResponseEntity<?> get(@RequestParam Map<String,String> params)
    {
        ArrayList<ArticleResponseDto> resp;

        resp = ArticleResponseDtoMapper.mapToDto(articleService.getArticles(params));

        return ResponseEntity.ok(resp);
    }

    //post de purchase, uso:
    //si no viene parametro en el header que indica el carrito entonces se genera un carrito nuevo
    //ese carrito nuevo se le asigna como id el id del primer ticket del carrito.
    //en el caso de agregar un ticket mas al carrito, entonces se especifica dicho id en el header
    //la salida se cambia para que retorne tambien el total del carrito ademas del total por ticket.
    //Los datos de los carritos y tickets son mantenidos en memoria.
    @PostMapping("/purchase-request")
    public ResponseEntity<?> purchase(@RequestBody PurchaseDto purchaseDto, @RequestHeader(required = false) Integer carrito)
    {
        var articulos = PurchaseArticleDtoMapper.mapToPurchaseArticle(purchaseDto.getArticles());
        Ticket ticket = articleService.newTicket(articulos, carrito);

        var resp = TicketDtoMapper.mapToDto(ticket);

        return ResponseEntity.ok(resp);
    }
}
