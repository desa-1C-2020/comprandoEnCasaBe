package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.UUID.randomUUID;

public class ShoppingList {
    private User user;
    private List<ItemsByCommerce> itemsByCommerce;
    private Date creationDateTime;
    private BigDecimal total;
    private String id;

    public ShoppingList(User user, List<ItemsByCommerce> itemsByCommerce, BigDecimal total, Date creationDateTime) {
        this.user = user;
        this.itemsByCommerce = itemsByCommerce;
        this.total = total;
        this.creationDateTime = creationDateTime;
        this.id = randomUUID().toString();
    }

    public BigDecimal getTotal() {
        return total;
    }
}
