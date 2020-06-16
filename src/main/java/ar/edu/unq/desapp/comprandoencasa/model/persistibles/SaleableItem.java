package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import static java.util.UUID.randomUUID;

public class SaleableItem {

    private String id;
    private int stock;
    private double price;
    private Product product;

    public SaleableItem(int stock, double price, Product product) {
        this.id = randomUUID().toString();
        this.stock = stock;
        this.price = price;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean sameId(String saleableId) {
        return id.equals(saleableId);
    }

    public boolean sameProduct(Product product) {
        return this.product.sameProduct(product);
    }

    public boolean sameProductId(String productId) {
        return this.product.sameId(productId);
    }

    public void updateWith(SaleableItem toUpdate) {
        setPrice(toUpdate.getPrice());
        setStock(toUpdate.getStock());
        this.product.updateWith(toUpdate.getProduct());
    }

    public String getProductId() {
        return product.getId();
    }

    public boolean containsInProductName(String productToFind) {
        return product.containsInName(productToFind);
    }
}
