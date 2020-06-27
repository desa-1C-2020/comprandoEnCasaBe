package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_method")
public class Product extends PersistibleSupport {
    private String name;
    private String brand;
    private String imageUrl;

    public Product(String name, String brand, String url) {
        this.name = name;
        this.brand = brand;
        this.imageUrl = url;
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

    public boolean sameId(Long productId) {
        return getId().equals(productId);
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
