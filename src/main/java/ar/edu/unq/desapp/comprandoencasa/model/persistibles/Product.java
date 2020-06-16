package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.apache.commons.lang3.StringUtils;

import static java.util.UUID.randomUUID;

public class Product {

    private String id;
    private String name;
    private String brand;
    private String imageUrl;

    public Product(String name, String brand, String url) {
        this.id = randomUUID().toString();
        this.name = name;
        this.brand = brand;
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
        return name.equals(product.getName()) && brand.equals(product.getBrand());
    }

    public Product updateWith(Product productToUpdate) {
        this.setName(productToUpdate.getName());
        this.setBrand(productToUpdate.getBrand());
        this.setImageUrl(productToUpdate.getImageUrl());
        return this;
    }

    public boolean containsInName(String productToFind) {
        boolean existsInName = StringUtils.containsIgnoreCase(name, productToFind);
        boolean existsInBrand = StringUtils.containsIgnoreCase(brand, productToFind);
        return existsInName || existsInBrand;
    }
}
