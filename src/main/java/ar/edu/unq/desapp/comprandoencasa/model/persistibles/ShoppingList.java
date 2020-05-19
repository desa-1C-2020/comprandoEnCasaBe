package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.UUID.randomUUID;

public class ShoppingList {
    public static final String itemsByCommerce_FIELD = "itemsByCommerce";
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItemsByCommerce> getItemsByCommerce() {
        return itemsByCommerce;
    }

    public void setItemsByCommerce(List<ItemsByCommerce> itemsByCommerce) {
        this.itemsByCommerce = itemsByCommerce;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean itIsFrom(User user) {
        return user.same(user);
    }

    public String getUserId() {
        return user.getUid();
    }
}
