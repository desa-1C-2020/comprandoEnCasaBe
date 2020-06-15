package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.math.BigDecimal;

public class ShoppingListItem {
    private Product product;
    private int quantity;
    private BigDecimal price;//precio que valia cuando se creo la lista de compras. Hay que quitar el precio del producto.

    public ShoppingListItem(Product product, int quantity, BigDecimal price) {

        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductId() {
        return product.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
