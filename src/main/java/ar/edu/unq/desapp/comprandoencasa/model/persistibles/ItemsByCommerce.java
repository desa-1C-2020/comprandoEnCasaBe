package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "items_by_commerce")
public class ItemsByCommerce extends PersistibleSupport {
    @ManyToOne
    private Commerce commerce;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ShoppingListItem> items;

    public ItemsByCommerce() {
    }

    public ItemsByCommerce(Commerce commerce, List<ShoppingListItem> shoppingList) {
        this.commerce = commerce;
        this.items = shoppingList;
    }

    public Commerce getCommerce() {
        return commerce;
    }

    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }

    public Long getCommerceId() {
        return commerce.getId();
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItem> items) {
        this.items = items;
    }
}
