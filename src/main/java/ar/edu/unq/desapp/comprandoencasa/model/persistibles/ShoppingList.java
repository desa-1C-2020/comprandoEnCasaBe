package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

public class ShoppingList {
    public static final String itemsByCommerce_FIELD = "itemsByCommerce";
    private User user;
    private List<ItemsByCommerce> itemsByCommerce;
    private LocalDateTime creationDateTime;
    private BigDecimal total;
    private String id;

    public ShoppingList(User user, List<ItemsByCommerce> itemsByCommerce, BigDecimal total, LocalDateTime creationDateTime) {
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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
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

    public boolean sameId(String shoppingListToDeleteId) {
        return id.equals(shoppingListToDeleteId);
    }

    public List<Commerce> getCommerces() {
        return itemsByCommerce
            .stream()
            .map(ItemsByCommerce::getCommerce)
            .collect(Collectors.toList());
    }
}
