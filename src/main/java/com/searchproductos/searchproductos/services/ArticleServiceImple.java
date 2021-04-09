package com.searchproductos.searchproductos.services;

import com.searchproductos.searchproductos.entities.*;
import com.searchproductos.searchproductos.exceptionsHandlers.ArticuloSinStockException;
import com.searchproductos.searchproductos.exceptionsHandlers.BadParameterException;
import com.searchproductos.searchproductos.repositories.ArticleRepository;
import com.searchproductos.searchproductos.repositories.VentasRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Service
public class ArticleServiceImple implements ArticleService
{
    private static Integer ids = 0;

    private final ArticleRepository articleRepository;
    private final VentasRepository ventasRepository;

    public ArticleServiceImple(ArticleRepository articleRepository, VentasRepository ventasRepository)
    {
        this.articleRepository = articleRepository;
        this.ventasRepository = ventasRepository;
    }

    //este metodo controla primeramente que los parametros sean validos, luego con los parametros validados
    //llama al repo con los filtros, si no hay filtros llama al getAll
    @SneakyThrows
    @Override
    public ArrayList<Article> getArticles(Map<String, String> params)
    {
        Map<String, String> filtrosValidos = new HashMap<>();
        ArrayList<Article> resp;

        int order = -1;

        if (params.isEmpty())
            resp = articleRepository.getAll();
        else {
            int cantFiltros = 0;
            for (Map.Entry<String, String> filtro : params.entrySet())
            {
                switch (filtro.getKey())
                {
                    case "productId":
                    case "name":
                    case "category":
                    case "brand":
                    case "freeShipping":
                    case "price":
                    case "prestige":
                        filtrosValidos.put(filtro.getKey(), filtro.getValue());
                        cantFiltros++;
                        break;
                    case "order":
                        order = Integer.parseInt(filtro.getValue());
                        break;
                    default:
                        throw new BadParameterException("Parametro no valido: " + filtro.getKey());
                }

                if (cantFiltros > 2)
                    throw new BadParameterException("Cantidad de parametros no valida: " + cantFiltros);
            }

            resp = articleRepository.getByFilters(filtrosValidos);

            //una vez filtrado el resultado, se ordena si es solicitado
            if (order != -1)
            {
                switch (order)
                {
                    case 0:
                        resp.sort(Comparator.comparing(Article::getName));
                        break;
                    case 1:
                        resp.sort(Comparator.comparing(Article::getName).reversed());
                        break;
                    case 2:
                        resp.sort(Comparator.comparing(Article::getPrice));
                        break;
                    case 3:
                        resp.sort(Comparator.comparing(Article::getPrice).reversed());
                        break;
                    default:
                        throw new BadParameterException("Parametro de ordenamiento no valido: " + order);
                }
            }
        }
        
        return resp;
    }

    @SneakyThrows
    @Override
    public Ticket newTicket(ArrayList<PurchaseArticle> articulos, Integer carrito)
    {
        double total = 0;
        ArrayList<PurchaseArticle> articulosEnTicket = new ArrayList<>();
        ArrayList<VentaArticulo> ventaArticulos = new ArrayList<>();

        for (PurchaseArticle p: articulos)
        {
            var article = articleRepository.getByPurchaseArticle(p);
            var venta = ventasRepository.getVentaArtbyId(article.getProductId());

            int disponibles = article.getQuantity() - venta.getVendidos();

            if (p.getQuantity() > disponibles)
                throw new ArticuloSinStockException("Articulo id " + article.getProductId() + " solo tiene " +
                                                    disponibles + " unidades disponibles");

            total += article.getPrice() * p.getQuantity();
            var purchaseArt = new PurchaseArticle(article.getProductId(), article.getName(), article.getBrand(), p.getQuantity());
            articulosEnTicket.add(purchaseArt);

            venta.addVendidos(p.getQuantity());
            ventaArticulos.add(venta);
        }

        Result resultado = new Result(200, "La solicitud de compra se completo con exito");

        var nuevoTicket = new Ticket(ids + 1, articulos, total, 0, resultado);

        //si no especifica carrito, entonces lo creo
        if (carrito == null)
            carrito = ventasRepository.newCarrito(nuevoTicket);
        else
            ventasRepository.addTicketToCarrito(nuevoTicket, carrito);

        double totalCarrito = ventasRepository.getTotalPorCarrito(carrito);

        nuevoTicket.setTotalCarrito(totalCarrito);

        ventasRepository.updateVentas(ventaArticulos);

        ids++;

        return nuevoTicket;
    }
}
