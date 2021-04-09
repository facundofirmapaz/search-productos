package com.searchproductos.searchproductos.dtos.mappers;

import com.searchproductos.searchproductos.dtos.TicketDto;
import com.searchproductos.searchproductos.entities.Ticket;

public class TicketDtoMapper
{
    public static TicketDto mapToDto(Ticket t)
    {
        var articles = PurchaseArticleDtoMapper.mapToDto(t.getArticles());

        var result = ResultDtoMapper.mapToDto(t.getStatusCode());

        return new TicketDto(t.getId(), articles, t.getTotal(), t.getTotalCarrito(), result);
    }
}
