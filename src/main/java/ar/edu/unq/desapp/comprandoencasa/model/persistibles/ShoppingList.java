package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shopping_list")
public class ShoppingList extends PersistibleSupport {
    public static final String itemsByCommerce_FIELD = "itemsByCommerce";
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemsByCommerce> itemsByCommerce;
    private LocalDateTime creationDateTime;
    private BigDecimal total;

    public ShoppingList() {
        //Used for hibernate
    }

    public ShoppingList(User user, List<ItemsByCommerce> itemsByCommerce, BigDecimal total, LocalDateTime creationDateTime) {
        this.user = user;
        this.itemsByCommerce = itemsByCommerce;
        this.total = total;
        this.creationDateTime = creationDateTime;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean itIsFrom(User user) {
        return user.same(user);
    }

    public Long getUserId() {
        return user.getId();
    }

    public boolean sameId(Long shoppingListToDeleteId) {
        return getId().equals(shoppingListToDeleteId);
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
