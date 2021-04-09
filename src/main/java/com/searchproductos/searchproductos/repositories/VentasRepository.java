package com.searchproductos.searchproductos.repositories;
import com.searchproductos.searchproductos.entities.Ticket;
import com.searchproductos.searchproductos.entities.VentaArticulo;

import java.util.ArrayList;

public interface VentasRepository
{
    VentaArticulo getVentaArtbyId(Integer productId);

    void updateVentas(ArrayList<VentaArticulo> ventaArticulos);

    void addTicketToCarrito(Ticket nuevoTicket, Integer carrito);

    Integer newCarrito(Ticket nuevoTicket);

    double getTotalPorCarrito(Integer carrito);
}
