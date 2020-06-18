package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import javax.validation.constraints.NotNull;

/**
 * Models the product added createAndSave frontend
 */
public class SaleableItemTO {
    private String productId;
    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private int stock;
    @NotNull
    private double price;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}