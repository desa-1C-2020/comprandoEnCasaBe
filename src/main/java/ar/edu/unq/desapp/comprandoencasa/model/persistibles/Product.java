package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import static java.util.UUID.randomUUID;

public class Product {

    private String id;
    private String name;
    private String brand;
    private int stock;
    private double price;
    private String imageUrl;

    public Product(String name, String brand, int stock, double price, String url) {
        this.id = randomUUID().toString();
        this.name = name;
        this.brand = brand;
        this.stock = stock;
        this.price = price;
        this.imageUrl = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public boolean sameId(String productId) {
        return id.equals(productId);
    }

    public boolean sameProduct(Product product) {
        return name.equals(product.getName()) &&
            brand.equals(product.getBrand()) &&
            imageUrl.equals(product.getImageUrl());
    }

    public Product updateWith(Product productToUpdate) {
        this.setName(productToUpdate.name);
        this.setBrand(productToUpdate.brand);
        this.setImageUrl(productToUpdate.imageUrl);
        this.setPrice(productToUpdate.price);
        this.setStock(productToUpdate.stock);
        return this;
    }
}
