package com.searchproductos.searchproductos.exceptionsHandlers;

public class ArticuloSinStockException extends Exception
{
    public ArticuloSinStockException(String m)
    {
        super(m);
    }
}
