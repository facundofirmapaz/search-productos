package com.searchproductos.searchproductos.repositories;
import com.searchproductos.searchproductos.entities.Ticket;
import com.searchproductos.searchproductos.entities.VentaArticulo;

import com.searchproductos.searchproductos.exceptionsHandlers.CarritoNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//repositorio de ventas en memoria
//se decide llevar el stock, llevando el control de ventas, el stock se calcula haciendo
//la diferencia del total original del articulo (el de la planilla) y el total vendido
@Repository
public class VentasRepositoryImple implements VentasRepository
{

    private static ArrayList<VentaArticulo> ventas = new ArrayList<>();
    private static Map<Integer, ArrayList<Ticket>> carritos = new HashMap<>();

    @SneakyThrows
    @Override
    public VentaArticulo getVentaArtbyId(Integer productId)
    {

        VentaArticulo ventaArticulo = null;

        //si ya tiene ventas lo devuelvo
        for (VentaArticulo v : ventas)
        {
            if (v.getProductId().equals(productId))
            {
                ventaArticulo = v;
                break;
            }
        }
        // si no lo encontro, lo agrego con ventas en 0
        if(ventaArticulo == null)
        {
            ventaArticulo = new VentaArticulo(productId, 0);
            ventas.add(ventaArticulo);
        }

        return ventaArticulo;
    }

    @SneakyThrows
    @Override
    public void updateVentas(ArrayList<VentaArticulo> ventaArticulos)
    {
        for (VentaArticulo v: ventaArticulos)
        {
            for (VentaArticulo venta : ventas)
            {
                if (v.getProductId().equals(v.getProductId()))
                {
                    venta.setVendidos(v.getVendidos());
                    break;
                }
            }
        }
    }

    @SneakyThrows
    @Override
    public void addTicketToCarrito(Ticket nuevoTicket, Integer carrito)
    {
        //si esta en el mapa, entonces agrego a la lista de tickets de dicho carrito
        if(carritos.containsKey(carrito))
            carritos.get(carrito).add(nuevoTicket);
        else
            throw new CarritoNotFoundException("No se encontro el carrito id: " + carrito);

    }

    @Override
    public Integer newCarrito(Ticket nuevoTicket)
    {
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(nuevoTicket);
        carritos.put(nuevoTicket.getId(), tickets);

        return nuevoTicket.getId();
    }

    @Override
    public double getTotalPorCarrito(Integer carrito)
    {
        var tickets = carritos.get(carrito);

        double totalCarrito = 0;

        for (Ticket t: tickets)
            totalCarrito += t.getTotal();

        return totalCarrito;
    }
}
