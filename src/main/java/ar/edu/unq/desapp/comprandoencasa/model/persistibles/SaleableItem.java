package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "saleable_item")
public class SaleableItem extends PersistibleSupport {
    private int stock;
    private double price;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    public SaleableItem() {
    }

    public SaleableItem(int stock, double price, Product product) {
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

    public boolean sameProduct(Product product) {
        return this.product.sameProduct(product);
    }

    public boolean sameProductId(Long productId) {
        return this.product.sameId(productId);
    }

    public void updateWith(SaleableItem toUpdate) {
        setPrice(toUpdate.getPrice());
        setStock(toUpdate.getStock());
        this.product.updateWith(toUpdate.getProduct());
    }

    public Long getProductId() {
        return product.getId();
    }

    public boolean containsInProductName(String productToFind) {
        return product.containsInName(productToFind);
    }

    public void decrementStockIn(int quantity) {
        stock -= quantity;
    }
}
