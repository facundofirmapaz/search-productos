package com.searchproductos.searchproductos.entities;

public class VentaArticulo
{
    private Integer productId;
    private Integer vendidos;

    public VentaArticulo(Integer productId, Integer vendidos) {
        this.productId = productId;
        this.vendidos = vendidos;
    }

    public VentaArticulo() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }

    public void addVendidos(Integer quantity) { vendidos = vendidos + quantity; }
}
