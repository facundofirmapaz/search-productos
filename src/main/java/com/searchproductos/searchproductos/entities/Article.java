package com.searchproductos.searchproductos.entities;

public class Article
{
    private Integer productId;
    private String name;
    private String category;
    private String brand;
    private double price;
    private Integer quantity;
    private boolean freeShipping;
    private String prestige;

    public Integer prestigeNumValue() { return prestige.length(); }

    public Article(Integer productId, String name, String category, String brand, double price, Integer stock, boolean freeShipping, String prestige) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = stock;
        this.freeShipping = freeShipping;
        this.prestige = prestige;
    }

    public Article() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getPrestige() {
        return prestige;
    }

    public void setPrestige(String prestige) {
        this.prestige = prestige;
    }

    public boolean evaluarFiltro(String key, String value)
    {
        boolean resp = false;
        switch (key)
        {
            case "productId":
                resp = productId == Integer.parseInt(value);
                break;
            case "name":
                resp = name.equals(value);
                break;
            case "category":
                resp = category.equals(value);
                break;
            case "brand":
                resp = brand.equals(value);
                break;
            case "price":
                resp = price == Double.parseDouble(value);
                break;
            case "freeShipping":
                resp = freeShipping == Boolean.parseBoolean(value);
                break;
            case "prestige":
                resp = prestige.equals(value);
                break;
        }

        return resp;
    }
}
